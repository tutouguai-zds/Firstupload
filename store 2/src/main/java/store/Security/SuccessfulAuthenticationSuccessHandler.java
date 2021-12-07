package store.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import store.POJO.Role;
import store.Service.Message;
import store.Service.UserDataService;

@Component
//登录成功处理类
public class SuccessfulAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	UserDataService uds;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setContentType("text/json;charset=utf-8");
		Message message=new Message();
		//获取用户的权限列表
		List<Role> list=uds.findUserByUsername(request.getParameter("username")).getAuthorities();
		List<String> role=new ArrayList<String>();
		//依次添加进role中,Role的tostrin已重写为返回权限的字符串类型
		for (Role i : list) {
			role.add(i.toString());
		}
		//判断用户类型，设置不同返回消息
		if(role.contains("ROLE_ADMIN")) {
			message.setMessage("欢迎回来，亲爱的管理员");
		}
		else if(role.contains("ROLE_USER")) {
			message.setMessage("亲爱的用户，欢迎回来");
		}
		
		message.setObject(uds.findUserByUsername(request.getParameter("username")));
		message.setStatuscode(200);
		response.getWriter().write(JSON.toJSONString(message));
	}

}
