package store.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.DAO.OrderRepo;
import store.POJO.Order;
import store.POJO.User;

@Service
public class OrderService {
	@Autowired
	private OrderRepo re;
	
	public List<Order> findAllByUser(User user){
		return re.findAllByUser(user);
	}
	public List<Order> findAll(){
		return (List<Order>) re.findAll();
	}
	public Object findByid(Long id) {
		return re.findById(id);
	}
	public boolean save(Order order) {
		return re.save(order)!=null;
	}
	public void deleteAll() {
		re.deleteAll();
	}
	public void deleteById(Long id) {
		re.deleteById(id);
	}
	public void deleteByUser(User user) {
		re.deleteAllByUser(user);
	}
}
