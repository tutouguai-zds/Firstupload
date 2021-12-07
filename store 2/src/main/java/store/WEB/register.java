package store.WEB;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import store.POJO.Role;
import store.POJO.User;
import store.Service.Message;
import store.Service.UserDataService;

@RestController
@RequestMapping("/reg")
@CrossOrigin
public class register {
	
	@Autowired
	UserDataService uds;
	
	@Autowired
	PasswordEncoder pe;
	@PostMapping
	@Transactional
	public Message setUser(@RequestBody User user) {
		Message m=new Message();
		if(uds.findUserByUsername(user.getUsername())!=null) {
			
			m.setMessage("你的账户已存在");
			return m;
		}
		//持久化数据库前先对密码加密
		user.setPassword(pe.encode(user.getPassword()));
		Role role=new Role();
		role.setAuthority("ROLE_USER");
		//必须对应设置好拥有权限的用户
		role.setUser(user);
		List <Role> list=user.getAuthorities();
		//实质上是在user里保存权限，list是user里的Roles属性的另一个引用
		list.add(role);
		uds.save(user);
		m.setMessage("注册成功");
		return m;
	}
}
