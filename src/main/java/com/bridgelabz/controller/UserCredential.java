package com.bridgelabz.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.User;
import com.bridgelabz.services.UserService;
import com.bridgelabz.token.GenerateJWT;
import com.bridgelabz.util.Encryption;
import com.bridgelabz.validator.RegistrationValidationImpl;
import com.bridgelabz.model.MyResponse;
import com.bridgelabz.model.Token;

@RestController
public class UserCredential {
	private Logger LOG = (Logger) LogManager.getLogger(UserCredential.class);

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

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<MyResponse> register(@RequestBody User user, HttpServletRequest request) {
		try {
			String url = request.getRequestURL().toString();
			int a = url.lastIndexOf("/");
			String url2 = url.substring(0, a);
			String password = user.getPassword();
			String encryptedPassword = encrypt.encryptPassword(password);
			user.setPassword(encryptedPassword);
			String regvValid = registerValidation.validator(user);
			LOG.info("validation checking"+regvValid);
			if (regvValid.equals("false")){
				LOG.info("user enter correct credential");
				userService.register(user);
				userService.sendMail("somasingh1701@gmail.com", user.getEmail(), "verifyUser", url);
				LOG.debug("user register success");
				LOG.info("sending verification mail to user");
			} else {
				LOG.error(regvValid);
				MyResponse.setResponseMessage(regvValid);
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(MyResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.catching(e);
			MyResponse.setResponseMessage("your credential is wrong");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MyResponse);
		}
		LOG.debug("registration is successfull");
		MyResponse.setResponseMessage("registration Success");
		return ResponseEntity.ok(MyResponse);

	}

	@RequestMapping("/verifyUser/{id}")
	public ResponseEntity<MyResponse> verify(@PathVariable("id") int id, HttpServletRequest request) {
		try {
			boolean isVerify;
			isVerify = userService.isActivated(id);
			if (isVerify) {
				LOG.debug("user verified success");
				MyResponse.setResponseMessage("user verified");
				return ResponseEntity.ok(MyResponse);

			} else {
				LOG.debug("user does not exist");
				MyResponse.setResponseMessage("user does not exist");
				return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(MyResponse);

			}
		} catch (Exception e) {
			LOG.catching(e);
			e.printStackTrace();
		}
		LOG.debug("user verification is sucessfully done");
		MyResponse.setResponseMessage("verification done");
		return ResponseEntity.ok(MyResponse);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<MyResponse> login(@RequestBody User user, HttpServletRequest request, HttpSession session) {
		String normalPassword = user.getPassword();
		String encryptedPassword = encrypt.encryptPassword(normalPassword);
		User userLogin = userService.login(user, encryptedPassword);
		request.setAttribute("user", userLogin);
		//session.setAttribute("userLogin", userLogin);
		if (userLogin == null) {
			LOG.debug("user enter wrong credential:-");
			MyResponse.setResponseMessage("wrong credential");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(MyResponse);
		}
		String accessToken = GenerateJWT.generateToken(userLogin.getId());
		token.setGenerateToken(accessToken);
		LOG.info("token generate:-" + accessToken);
		String url = request.getRequestURL().toString();
		url = url.substring(0, url.lastIndexOf("/")) + "/" + "finalLogin" + "/" + accessToken;
		try {
			// userService.sendMail("somasingh1701@gmail.com", user.getEmail(),
			// "finalLogin", url);
			LOG.debug("after loggged in, sending token via mail");
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("user successfully logged in");
		MyResponse.setResponseMessage(accessToken);
		return ResponseEntity.ok(MyResponse);
	}

	@RequestMapping("/finalLogin/{token}")
	public ResponseEntity<String> checkValidUser(@PathVariable("token") String generateToken) {

		Token token = userService.getToken(generateToken);
		if (token == null) {
			LOG.debug("your token does not exist please enter correct token");
			return new ResponseEntity<String>("token is incorrect", HttpStatus.BAD_REQUEST);
		}
		if (token.getGenerateToken().equals(generateToken)) {
			LOG.debug("your token is matched with redis's token");
			return new ResponseEntity<String>("successfull login", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Unsuccessfull login", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/forgotpassword/")
	public ResponseEntity<String> forgotPassword(@RequestBody User user, HttpServletRequest request) {
		String url = request.getRequestURL().toString();
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

	@RequestMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestBody User user) {
		userService.resetPassword(user.getEmail(), user.getPassword());
		System.out.println("password reset");
		return new ResponseEntity<String>("password reset successfully", HttpStatus.OK);

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<MyResponse> logout(HttpSession session) {
		session.removeAttribute("userLogin");
		session.invalidate();
		MyResponse.setResponseMessage("logout successfully:-");
		return ResponseEntity.ok(MyResponse);
	}

}