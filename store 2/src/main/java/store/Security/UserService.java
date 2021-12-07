package store.Security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import store.DAO.UserRepo;
import store.POJO.User;
@Service
//自定义用户详情服务
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepo ur;

	@Override
	//根据获取的用户名执行相应操作，返回异常或者返回对应对象以进行下一步骤
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=ur.findByUsername(username);
		if(user!=null) {
			return user;
		}
		throw new UsernameNotFoundException(username+"Not Found");
	}

}
