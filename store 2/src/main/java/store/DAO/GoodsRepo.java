package store.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

import store.POJO.Goods;
@CrossOrigin
public interface GoodsRepo extends CrudRepository<Goods, Long> {

	Goods findByName(String name);

	List<Goods> findBycategory(String category);

	void deleteByCategory(String category);
	
}