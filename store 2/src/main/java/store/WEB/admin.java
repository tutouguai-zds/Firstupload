package store.WEB;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import store.POJO.Role;
import store.POJO.User;
import store.Service.Message;
import store.Service.RoleService;
import store.Service.UserDataService;

@RestController
@CrossOrigin
@RequestMapping("/admin/cguser")
public class admin {
	
	@Value("${upload.path}") private String path;
	@Autowired
	PasswordEncoder pe;
	@Autowired
	UserDataService uds;
	@Autowired
	RoleService rs;
	@Transactional
	@PostMapping
	public Message cguser(@RequestParam MultipartFile head,@RequestParam String form,@RequestParam String role) throws IOException {
		Message mes=new Message();
		User user=JSON.parseObject(form,User.class);
		User u=uds.findUserByUsername(user.getUsername());
		rs.deleteRoleByUser(u);
		user.setId(u.getId());
		user.setPassword(pe.encode(user.getPassword()));
		File file=new File(path);
		if(!file.exists()) {
			file.mkdir();
		}
		String realpath=path+"+"+head.getOriginalFilename();
		File headfile=new File(realpath);
		if(!headfile.exists()) {
			headfile.createNewFile();
			head.transferTo(headfile);
		}
		user.setHead("/headimg/"+head.getOriginalFilename());
		if(role.equals("ROLE_USER")) {
			Role r=new Role();
			r.setAuthority("ROLE_USER");
			r.setUser(user);
			List<Role> au=new ArrayList<Role>();
			au.add(r);
			user.setRole(au);
		}else {
			Role r=new Role();
			r.setAuthority("ROLE_ADMIN");
			r.setUser(user);
			List<Role> au=new ArrayList<Role>();
			au.add(r);
			user.setRole(au);
		}
		uds.update(user);
		user.setRole(null);
		mes.setMessage("更改成功");
		mes.setObject(user);
		mes.setStatuscode(200);
		return mes;
	}
}
