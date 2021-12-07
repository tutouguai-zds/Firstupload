package store.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.DAO.GoodsRepo;
import store.POJO.Goods;

@Service
public class GoodsService {
	
	@Autowired
	private GoodsRepo goods;
	
	public Goods findGoodsByName(String name) {
		return goods.findByName(name);
	}
	
	public List<Goods> findAll(){
		return (List<Goods>) goods.findAll();
	}
	public List<Goods> findGoodsByCategory(String category){
		return goods.findBycategory(category);
	}
	public Object findById(Long id) {
		return goods.findById(id);
	}
	public Goods save(Goods goods) {
		return this.goods.save(goods);
	}
	public void deleteById(Long id) {
		goods.deleteById(id);
	}
	public void deleteAll() {
		goods.deleteAll();
	}
	public void deleteAllByCategory(String category) {
		goods.deleteByCategory(category);
	}
}
