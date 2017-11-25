package com.bridgelabz.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.model.MyResponse;
import com.bridgelabz.model.Token;
import com.bridgelabz.model.User;
import com.bridgelabz.services.UserService;
import com.bridgelabz.token.GenerateJWT;
import com.bridgelabz.token.VerifyJWT;
import com.bridgelabz.util.Encryption;
import com.bridgelabz.util.MailUtil;
import com.bridgelabz.validator.RegistrationValidationImpl;

/**
 * @author Soma Singh
 * @see class for user related task
 */
@RestController
public class UserController {
	private Logger LOG = (Logger) LogManager.getLogger(UserController.class);

	@Autowired
	RegistrationValidationImpl registerValidation;

	@Autowired
	UserService userService;

	@Autowired
	MyResponse MyResponse;

	@Autowired
	Token token;
	
	@Autowired
	Encryption encrypt;
	
	@Autowired
	MailUtil mailUtil;

	/**
	 * @param user	
	 * @param request
	 * @return MyResponse
	 * @see this method is for user registration and authenticate user
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<MyResponse> register(@RequestBody User user, HttpServletRequest request) {
		try {
			String url =  "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/verifyUser";
			String password = user.getPassword();
			String encryptedPassword = encrypt.encryptPassword(password);
			user.setPassword(encryptedPassword);
			String regvValid = registerValidation.validator(user);
			if (regvValid.equals("true")){
				userService.register(user);
				mailUtil.sendMail("somasingh1701@gmail.com", user.getEmail(), "verifyUser", url+user.getId());
			} else {
				MyResponse.setResponseMessage(regvValid);
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(MyResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.catching(e);
			MyResponse.setResponseMessage("your credential is wrong");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MyResponse);
		}
		MyResponse.setResponseMessage("registration Success");
		return ResponseEntity.ok(MyResponse);

	}

	/**
	 * @param id
	 * @param request
	 * @return MyResponse
	 * @see this methos is for aunthenticating user
	 */
	@RequestMapping(value="/verifyUser/{id}",method = RequestMethod.PUT)
	public ResponseEntity<MyResponse> verify(@PathVariable("id") int id, HttpServletRequest request) {
		try {
			boolean isVerify;
			isVerify = userService.isActivated(id);
			if (isVerify) {
				MyResponse.setResponseMessage("user verified");
				return ResponseEntity.ok(MyResponse);

			} else {
				MyResponse.setResponseMessage("user does not exist");
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(MyResponse);

			}
		} catch (Exception e) {
			LOG.catching(e);
			e.printStackTrace();
		}
		MyResponse.setResponseMessage("verification done");
		return ResponseEntity.ok(MyResponse);
	}

	/**
	 * @param user
	 * @param request
	 * @param session
	 * @return MyResponse
	 * @see this method is for user login and validating
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<MyResponse> login(@RequestBody User user, HttpServletRequest request, HttpSession session) {
		String normalPassword = user.getPassword();
		String encryptedPassword = encrypt.encryptPassword(normalPassword);
		User userLogin = userService.login(user, encryptedPassword);
		//session.setAttribute("userLogin", userLogin);
		if (userLogin == null) {
			MyResponse.setResponseMessage("wrong credential");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MyResponse);
		}
		String accessToken = GenerateJWT.generateToken(userLogin.getId());
//		int userId = VerifyJWT.verifyAccessToken(request.getHeader("accessToken").toString());
//		User user1 = userService.getUserById(userId);
		token.setGenerateToken(accessToken);
		String url = request.getRequestURL().toString();
		url = url.substring(0, url.lastIndexOf("/")) + "/" + "finalLogin" + "/" + accessToken;
		try {
			// userService.sendMail("somasingh1701@gmail.com", user.getEmail(),
			// "finalLogin", url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		MyResponse.setResponseMessage(accessToken);
		return ResponseEntity.ok(MyResponse);
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public ResponseEntity<User> getUser(HttpServletRequest request) {
		int userId = VerifyJWT.verifyAccessToken(request.getHeader("accessToken").toString());
		User user = userService.getUserById(userId);
		
		return ResponseEntity.ok(user);
	}
	/**
	 * @param generateToken
	 * @return MyResponse
	 * @see this method is for validating token in redis
	 */
	@RequestMapping("/finalLogin/{token}")
	public ResponseEntity<String> checkValidUser(@PathVariable("token") String generateToken) {

		Token token = userService.getToken(generateToken);
		if (token == null) {
			return new ResponseEntity<String>("token is incorrect", HttpStatus.BAD_REQUEST);
		}
		if (token.getGenerateToken().equals(generateToken)) {
			return new ResponseEntity<String>("successfull login", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Unsuccessfull login", HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param user
	 * @param request
	 *  @return MyResponse
	 * @see this method is for forgot password, sending mail of forgot password to user
	 */
	@RequestMapping("/forgotpassword")
	public ResponseEntity<String> forgotPassword(@RequestBody User user, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		try {
			URL lUrl = new URL(url);
			System.out.println(lUrl.getHost());
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}   
		int lastIndex = url.lastIndexOf("/");
		String urlofForgotPassword = url.substring(0, lastIndex);
		try {
			userService.sendMail("somasingh1701@gmail.com", user.getEmail(), "resetPassword",
					urlofForgotPassword + "/" + "resetPassword");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("url of forgotPassword has been sent", HttpStatus.OK);

	}

	/**
	 * @param user
	 * @return MyResponse
	 * @see this nethod is for reseting password
	 */
	@RequestMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestBody User user) {
		userService.resetPassword(user.getEmail(), user.getPassword());
		return new ResponseEntity<String>("password reset successfully", HttpStatus.OK);

	}

	/**
	 * @param session
	 * @return MyResponse
	 * @see this meythod is for logout 
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<MyResponse> logout(HttpSession session,HttpServletResponse response) {
		session.removeAttribute("userLogin");
		session.invalidate();
		try {
			response.sendRedirect("login");
			response.setHeader("Cache-Control", "no-cache,no-store,must-validate");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Expires","0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MyResponse.setResponseMessage("logout successfully:-");
		return ResponseEntity.ok(MyResponse);
	}

}