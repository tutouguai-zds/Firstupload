package store.POJO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
@Data
@Entity
@Table(name="role")
//自实现GrantedAuthority，重写tostring返回权限字符串
public class Role implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;					//id
	private String authority;			//权限
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;					//权限指定的用户
	@Override
	public String getAuthority() {
		return this.authority;
	}
	public String toString() {
		return this.authority;
	}
}
