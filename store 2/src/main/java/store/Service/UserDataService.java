package store.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.DAO.UserRepo;
import store.POJO.User;

@Service
public class UserDataService {
	@Autowired
	private UserRepo ur;
	
	//注册使用的保存信息方法
	public User save(User user) {
		if(!has(user)) {
			return ur.save(user);
		}
		return null;
	}
	//判断用户存不存在
	public boolean has(User user) {
		if(ur.existsById(user.getId())) {
			return true;
		}
		return false;
	}
	//修改用户信息时使用的方法
	public void update(User user) {
		ur.save(user);
	}
	//根据用户名查询用户
	public User findUserByUsername(String username) {
		return ur.findByUsername(username);
	}
	//根据id找到用户
	public Object findUserById(Long id) {
		return ur.findById(id);
	}
	public void deleteUserById(Long id) {
		ur.deleteById(id);
	}
	public void deleteUserByUser(User user) {
		ur.delete(user);
	}
	public void deleteAll() {
		ur.deleteAll();
	}
	public List<User> findAll() {
		
		return (List<User>) ur.findAll();
	}
}
