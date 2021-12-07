package store.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import store.Service.Message;

@Component
//匿名登录处理类
public class AnonymousAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
			Message message=new Message();
			message.setMessage("你尚未登录");
			message.setStatuscode(403);
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().write(JSON.toJSONString(message));
	}

}
