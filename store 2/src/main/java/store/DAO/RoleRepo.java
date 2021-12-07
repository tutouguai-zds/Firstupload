package store.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import store.POJO.Role;
import store.POJO.User;
@CrossOrigin
public interface RoleRepo extends CrudRepository<Role, Long> {

	List<Role> findByUser(User user);

	void deleteByUser(User user);

	List<Role> findAllByUser(User user);

}
