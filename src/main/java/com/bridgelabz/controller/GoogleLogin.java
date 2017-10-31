package com.bridgelabz.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.model.MyResponse;
import com.bridgelabz.services.UserService;
import com.bridgelabz.util.GoogleUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class GoogleLogin {
	private Logger LOG = (Logger) LogManager.getLogger(GoogleLogin.class);
	 
	
	@Autowired
	 UserService userService;
	
	@Autowired
	MyResponse myResponse;
	
	@RequestMapping(value="/googleLogin")
	public void googleLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String googleUrl=GoogleUtil.generateGoogleUrl();
		try {
			response.sendRedirect(googleUrl);
			//System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("testing i'm right");
	}
	@RequestMapping(value="/successGoogleLogin")
	public ResponseEntity<MyResponse> successGoogleLogin(HttpServletRequest request, HttpServletResponse response) {
		
		String code = (String)request.getParameter("code");
		LOG.info("code"+code);
		String accessToken = GoogleUtil.getAccessToken(code);
		LOG.info("accessToken"+accessToken);
		String googleProfileInfo = GoogleUtil.getProfileData(accessToken);
		LOG.info(googleProfileInfo);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String email = objectMapper.readTree(googleProfileInfo).get("email").asText();
			String id = objectMapper.readTree(googleProfileInfo).get("id").asText();
			String verified_email = objectMapper.readTree(googleProfileInfo).get("verified_email").asText(); 
			String given_name = objectMapper.readTree(googleProfileInfo).get("given_name").asText();
			String family_name=objectMapper.readTree(googleProfileInfo).get("family_name").asText();
			String gender = objectMapper.readTree(googleProfileInfo).get("gender").asText();
			LOG.info(email);
			LOG.info(id);
			LOG.info(verified_email);
			LOG.info(given_name);
			LOG.info(family_name);
			LOG.info(gender);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myResponse.setResponseMessage(accessToken);
		return ResponseEntity.ok(myResponse);
	}
}
