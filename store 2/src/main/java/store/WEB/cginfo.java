package store.WEB;

import java.io.File;
import java.io.IOException;
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
@RequestMapping("/cginfo")
@CrossOrigin
public class cginfo {
	
	//上传用户头像文件的根路径
	@Value("${upload.path}") private String uploadpath;
	@Autowired
	UserDataService uds;
	
	@Autowired
	PasswordEncoder pe;
	@Autowired
	RoleService rs;
	
	@Transactional
	@PostMapping
	public Message cg(@RequestParam MultipartFile head,@RequestParam String user) throws IllegalStateException, IOException {

		//将得到的JSON格式字符串转化为对象
		User u=JSON.parseObject(user,User.class);
		Message mes=new Message();
		u.setPassword(pe.encode(u.getPassword()));
		//此时获取的role列表已经指定了User，无需再像注册时指定
		List<Role> roles=rs.findRoleByUser(u);
//		//此处是把从数据获取回来的Role对象都解绑User，防止JSON无限循环 但新加入的数据库记录会没有权限 不要使用
//		for (Role role : roles) {
//			role.setUser(null);
//		}
		List<Role> list=u.getAuthorities();
		list=roles;
		//由于传过来的用户个人信息不涉及到更改权限，没有传递权限信息，所以可以直接从数据库根据User获取并设置到这里，并设置好对应映射
		u.setRole(roles);
		File file=new File(uploadpath);
		//上传文件的根目录是否存在，不存在就创建
		if(!file.exists()) {
			file.mkdir();
		}
		//文件的具体位置
		String path=uploadpath+"/"+head.getOriginalFilename();
		File uploadfile=new File(path);
		//文件是否已存在，若不存在再存储
		if(!uploadfile.exists()) {
			uploadfile.createNewFile();
			head.transferTo(uploadfile);
		}
		u.setHead("/headimg/"+head.getOriginalFilename());
		uds.update(u);
		u.setRole(null);
		//创建消息对象，给前端返回
		mes.setMessage("更改信息成功");
		mes.setObject(u);
		mes.setStatuscode(200);
		return mes;
	}
}
