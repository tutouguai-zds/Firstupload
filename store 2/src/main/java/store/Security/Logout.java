package store.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import store.Service.Message;

@Component
//注销成功处理器类
public class Logout implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		Message mes=new Message();
		mes.setMessage("注销成功");
		mes.setStatuscode(200);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(mes));
	}

}
