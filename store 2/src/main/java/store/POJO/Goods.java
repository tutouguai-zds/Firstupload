package store.POJO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

//相应注解作用已在order实体类中阐述
@Data
@Entity
@Table(name = "goods")
public class Goods {
	//此处发现一坑 如若有任意属性设置为final，则用JSON。parseObject方法将其转化为对象的时候将无法正确获取属性数据
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;			//商品ID
	private String name;		//商品名称
	private String des;			//商品描述
	private String images;		//商品图片
	private String inventory;	//商品库存
	private String category;	//商品类别
	@ManyToOne(targetEntity = Order.class)
	@JoinColumn(name = "order_id")
	private Order order;
}