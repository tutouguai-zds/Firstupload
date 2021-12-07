package store.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import store.Service.Message;
import store.Service.UserDataService;

@Component
//登录错误失败处理类
public class FailuredAuthenticaltionFailuredHandler implements AuthenticationFailureHandler {

	@Autowired
	UserDataService uds;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//设置相应体类型
		response.setContentType("text/json;charset=utf-8");
		Message message=new Message();
		if(uds.findUserByUsername(request.getParameter("username"))==null) {
			message.setMessage("登录失败,用户名不存在");
		}
		else {
			message.setMessage("密码错误");
		}
		message.setStatuscode(404);
		//写入之前通过使用fastJSON对对象进行转化处理
		response.getWriter().write(JSON.toJSONString(message));
	}

}
