package com.bridgelabz.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.User;
import com.bridgelabz.services.UserService;
import com.bridgelabz.validator.RegistrationValidationImpl;
import com.bridgelabz.model.MyErrorMessage;
import com.bridgelabz.model.Token;

@RestController
public class UserCredential {

	@Autowired
	RegistrationValidationImpl registerValidation;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MyErrorMessage errorMessage;
	
	@Autowired
	Token token;
	
		@RequestMapping(value = "/register", method = RequestMethod.POST)  
		 public ResponseEntity<MyErrorMessage>  register(@RequestBody User user, HttpServletRequest request) {  
		  try { 
			  
			  String url= request.getRequestURL().toString();
			  int a = url.lastIndexOf("/");
			  String url2 = url.substring(0, a);
			  boolean regvValid = registerValidation.validator(user);
			  if(regvValid) {
			  userService.register(user);
			  userService.sendMail("somasingh1701@gmail.com", user.getEmail(), "emailVerification",url2+"/"+"verifyUser"+"/"+user.getId());
			  }
			  else {
				  errorMessage.setErrorMessage("your credential is wrong");
				  return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorMessage);
			  }
		  } catch (Exception e) {
			  e.printStackTrace();
			  errorMessage.setErrorMessage("your credential is wrong");
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		  }
		  errorMessage.setErrorMessage("registration Success");
		return  ResponseEntity.ok(errorMessage);
		 
	
		 }
		@RequestMapping("/verifyUser/{id}")
		public void verify(@PathVariable("id") int id, HttpServletRequest request) {
			try {
				userService.isActivated(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
		}
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public ResponseEntity<MyErrorMessage>  login(@RequestBody User user,HttpServletRequest request) {
			
			User userLogin = userService.login( user);
			if(userLogin == null) {
				errorMessage.setErrorMessage("invalid user unable to login");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
			}
			String accessToken = UUID.randomUUID().toString().replaceAll("-", "");
			System.out.println(accessToken);
			token.setGenerateToken(accessToken);
			userService.saveTokenInRedis(token);
			String url = request.getRequestURL().toString();
			url = url.substring(0,url.lastIndexOf("/"))+"/"+"finalLogin"+"/"+accessToken;
			try {
				userService.sendMail("somasingh1701@gmail.com", user.getEmail(), "finalLogin", url);
			} catch (Exception e) {
				e.printStackTrace();
			}
			errorMessage.setErrorMessage("login successfully");
			return ResponseEntity.ok(errorMessage);
			
		}
		
	@RequestMapping("/finalLogin/{token}")
		public ResponseEntity<String> checkValidUser(@PathVariable("token") String generateToken){
		
		Token token = userService.getToken(generateToken);
		if (token == null) {
			return new ResponseEntity<String>("Unsuccessfull",HttpStatus.BAD_REQUEST);
		}
		if(token.getGenerateToken().equals(generateToken))
			return new ResponseEntity<String>("successfull login",HttpStatus.OK);
		return new ResponseEntity<String>("Unsuccessfull login",HttpStatus.BAD_REQUEST);
	}
		 
		@RequestMapping("/forgotpassword")
		public  ResponseEntity<String> forgotPassword(@RequestBody User user,HttpServletRequest request){
			String url = request.getRequestURL().toString();
			int lastIndex = url.lastIndexOf("/");
			String urlofForgotPassword = url.substring(0,lastIndex);
			try {
				userService.sendMail("somasingh1701@gmail.com", user.getEmail(), "resetPassword", urlofForgotPassword+"/"+"resetPassword");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ResponseEntity<>("url of forgotPassword has been sent",HttpStatus.OK);
			
		}
		@RequestMapping("/resetPassword")
		public ResponseEntity<String>  resetPassword(@RequestBody User user) {
			userService.resetPassword(user.getEmail(),user.getPassword());
			System.out.println("password reset");
			return new ResponseEntity<String> ("password reset successfully",HttpStatus.OK);
	
		}
		

}