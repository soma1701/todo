package com.bridgelabz.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.model.MyResponse;
import com.bridgelabz.model.User;
import com.bridgelabz.services.UserService;
import com.bridgelabz.token.GenerateJWT;
import com.bridgelabz.util.FBUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class FBLogin {
	private Logger LOG = (Logger) LogManager.getLogger(FBLogin.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	MyResponse myResponse;
	
	@RequestMapping(value="/fbLogin")
	public void fbLogin(HttpServletRequest request,HttpServletResponse response) {
		String fbUrl = FBUtil.generateFbUrl();
		try {
			LOG.info("FB URL: " + fbUrl);
			response.sendRedirect(fbUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/successFbLogin", method = RequestMethod.GET)
	public ResponseEntity<MyResponse> getFbAccessToken(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		LOG.info("After success");
		String codeForFb = request.getParameter("code");
		LOG.info("codeForFb:-"+codeForFb);
		String accessTokenForFb = FBUtil.getFbAccessToken(codeForFb);
		LOG.info("accessTokenForFb:-"+accessTokenForFb);
		String profileInfoFromFB = FBUtil.getProfileInfoFromFb(accessTokenForFb);
		LOG.info(profileInfoFromFB);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String email = mapper.readTree(profileInfoFromFB).get("email").asText();
			LOG.info(email);
			User userByEmail = userService.getUserByEmail(email);
			LOG.info("userByEmail:-"+userByEmail);
			if(userByEmail==null) {
				User userForFb = new User();
				userForFb.setEmail(mapper.readTree(profileInfoFromFB).get("email").asText());
				userForFb.setFirstName(mapper.readTree(profileInfoFromFB).get("first_name").asText());
				userForFb.setLastName(mapper.readTree(profileInfoFromFB).get("last_name").asText());
				userForFb.setValid(true);
				userService.register(userForFb);
				
				response.sendRedirect("http://localhost:8080/todo/#!/homePage");
			}else {
				String myAccessToken = GenerateJWT.generateToken(userByEmail.getId());
				LOG.info("token geneted by jwt"+myAccessToken);
				session.setAttribute("myAccessToken", myAccessToken);
				response.sendRedirect("http://localhost:8080/todo/#!/dummyFbLogin");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			LOG.info("exception occured during registering user from fb:");
			LOG.catching(e);
			e.printStackTrace();
		}
		return null;
		
	}
	@RequestMapping(value="/tokenAftergFbLogin")
	public ResponseEntity<MyResponse> getAccessTokenByglogin(HttpSession session){
		String acessToken = (String) session.getAttribute("myAccessToken");
		myResponse.setResponseMessage(acessToken);
		return ResponseEntity.ok(myResponse);
		
	}

}
