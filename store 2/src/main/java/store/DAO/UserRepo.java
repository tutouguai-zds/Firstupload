package store.DAO;



import org.springframework.data.repository.CrudRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

import store.POJO.User;
@CrossOrigin
public interface UserRepo extends CrudRepository<User, Long> {

	User findByUsername(String username);

}
