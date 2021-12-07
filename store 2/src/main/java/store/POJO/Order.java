package store.POJO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
//自动生成getset方法和tostring方法
@Data
//表示此pojo将会映射到数据库实体
@Entity
//映射过去的名字,由于order为mysql的关键字，所以只能加个1
@Table(name = "order1")
public class Order {
	//表的索引，生成策略为自动
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;											//订单ID
	@OneToMany(cascade = CascadeType.MERGE,mappedBy = "order")		//一对多关系 级联保存操作，mappedby为 Goods类中many to one 的对象属性名称
	private List<Goods> goods;										//订单商品列表
	private String date;											//创建时间
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;												//订单属于的用户
}
