package store.POJO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
@Table(name = "User")

public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id=0L;				//id
	private String username;		//账号
	private String password;		//密码
	private String telphone;		//手机号码
	private String remark;			//备注
	private String city;			//城市
	private String province;			//省份
	private String address;			//地址
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
//	防止JSON资源陷入1对多，多对1的死循环而报错
	@JsonIgnore
	private List<Role> role=new ArrayList<Role>();			//权限集合
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Order> orders;			//所创建的订单
	private String Head;				//头像
	@Override
	//从Userdetails继承过来的方法用于获取权限
	public List<Role> getAuthorities() {
		// TODO Auto-generated method stub
		return this.role;
	}
	@Override
	//用户有没有过期
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	//有没有被锁定
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	//权限凭证有没有过期
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	//可不可用
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
