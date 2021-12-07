package store.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.DAO.RoleRepo;
import store.POJO.Role;
import store.POJO.User;

@Service
public class RoleService {
	@Autowired
	private RoleRepo roleRepo;
	
	public List<Role> findRoleByUser(User user) {
		return roleRepo.findAllByUser(user);
	}
	public Role save(Role role) {
		return roleRepo.save(role);
	}
	public void deleteRole(Role role) {
		roleRepo.delete(role);
	}
	public void deleteAll() {
		roleRepo.deleteAll();
	}
	public void deleteRoleByUser(User user) {
		roleRepo.deleteByUser(user);
	}
}
