package com.bridgelabz.filter;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.bridgelabz.model.MyResponse;
import com.bridgelabz.model.User;
import com.bridgelabz.token.VerifyJWT;
import com.google.gson.Gson;

public class LogInIntercepter implements HandlerInterceptor{
	 private static final Logger LOG = LoggerFactory.getLogger(LogInIntercepter.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		LOG.info("access token is:-"+request.getHeader("accessToken"));
		System.out.println("shit: " + request.getHeader("accessToken"));
		int userId = VerifyJWT.verifyAccessToken(request.getHeader("accessToken").toString());
		LOG.info("user id is:-"+userId);
		if(userId == 0) {
			MyResponse myResponse = new MyResponse();
			myResponse.setResponseMessage("user logged In via accessToken");
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			String jsonResponse = gson.toJson(myResponse);
			out.println("json response"+jsonResponse);
			response.sendError(511);
			return false;
		}
		User user = new User();
		user.setId(userId);
		request.setAttribute( "user",user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception
	{
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
		
	}

}
