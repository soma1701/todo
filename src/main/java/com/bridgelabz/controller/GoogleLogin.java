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
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.model.MyResponse;
import com.bridgelabz.model.User;
import com.bridgelabz.services.UserService;
import com.bridgelabz.token.GenerateJWT;
import com.bridgelabz.util.GoogleUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Soma Singh
 * @see class related for google login
 */
@RestController
public class GoogleLogin {
	private Logger LOG = (Logger) LogManager.getLogger(GoogleLogin.class);
	 
	
	@Autowired
	 UserService userService;
	
	@Autowired
	MyResponse myResponse;
	
	/**
	 * @param request
	 * @param response
	 * @see method for getting url from googlelogin
	 */
	@RequestMapping(value="/googleLogin")
	public void googleLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String googleUrl=GoogleUtil.generateGoogleUrl();
		try {
			response.sendRedirect(googleUrl);
		} catch (IOException e) {
			LOG.catching(e);
			e.printStackTrace();
		}
	}
	/**
	 * @param request
	 * @param response
	 * @see method for getting code and accesstoken for google login
	 */
	@RequestMapping(value="/successGoogleLogin")
	public ResponseEntity<MyResponse> successGoogleLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		
		String code = (String)request.getParameter("code");
		String accessToken = GoogleUtil.getAccessToken(code);
		String googleProfileInfo = GoogleUtil.getProfileData(accessToken);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String email = objectMapper.readTree(googleProfileInfo).get("email").asText();
			User userByEmail = userService.getUserByEmail(email);
			if(userByEmail==null) {
				User googleUser = new User();
				googleUser.setEmail(objectMapper.readTree(googleProfileInfo).get("email").asText());
				googleUser.setFirstName(objectMapper.readTree(googleProfileInfo).get("given_name").asText());
				googleUser.setLastName(objectMapper.readTree(googleProfileInfo).get("family_name").asText());
				googleUser.setValid(true);
				userService.register(googleUser);
				String myAccessToken = GenerateJWT.generateToken(userByEmail.getId());
				session.setAttribute("myAccessToken", myAccessToken);
				response.sendRedirect("http://localhost:8080/todo/#!/homePage");
			}
			else {
				String myAccessToken = GenerateJWT.generateToken(userByEmail.getId());
				session.setAttribute("myAccessToken", myAccessToken);
				response.sendRedirect("http://localhost:8080/todo/#!/dummyLogin");
			}
			
			String id = objectMapper.readTree(googleProfileInfo).get("id").asText();
			String verified_email = objectMapper.readTree(googleProfileInfo).get("verified_email").asText(); 
			String given_name = objectMapper.readTree(googleProfileInfo).get("given_name").asText();
			String family_name=objectMapper.readTree(googleProfileInfo).get("family_name").asText();
			String gender = objectMapper.readTree(googleProfileInfo).get("gender").asText();
			
		} catch (IOException e) {
			LOG.info("exception while user does not have google account or user is not register with google");
			LOG.catching(e);;
			e.printStackTrace();
		} catch (Exception e) {
			LOG.info("exception while registering google user to my app");
			LOG.catching(e);;
			e.printStackTrace();
		}
		myResponse.setResponseMessage(accessToken);
		return ResponseEntity.ok(myResponse);
	}
	/**
	 * @param request
	 * @param response
	 * @see method for getting token after google login
	 */
	@RequestMapping(value="/tokenAftergLogin")
	public ResponseEntity<MyResponse> getAccessTokenByglogin(HttpSession session){
		String acessToken = (String) session.getAttribute("myAccessToken");
		myResponse.setResponseMessage(acessToken);
		return ResponseEntity.ok(myResponse);
		
	}
	
}
