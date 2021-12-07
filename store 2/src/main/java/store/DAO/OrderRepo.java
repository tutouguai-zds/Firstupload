package store.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

import store.POJO.Order;
import store.POJO.User;

@CrossOrigin
public interface OrderRepo extends CrudRepository<Order, Long> {

	List<Order> findAllByUser(User user);

	void deleteAllByUser(User user);

}
