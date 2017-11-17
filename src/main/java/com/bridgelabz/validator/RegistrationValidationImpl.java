package com.bridgelabz.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.model.User;
import com.bridgelabz.services.UserService;

public class RegistrationValidationImpl {

	@Autowired
	UserService userService;
	
	public String validator(User user) {
		String isValid = "true";
		String regexForEmail = "[a-z0-9]+@[a-z]+.[a-z]{3}";
		String regexForName="[A-Za-z]{2,10}";
		String regexForMobNo="[0-9]{10}";
		String regexForPassword="[A-Za-z0-9]{1,}";
		if(user.getFirstName()=="" && user.getFirstName()==null) {
			isValid = "your firstName can't be empty";
			return isValid;
		}else if(!user.getFirstName().matches(regexForName)){
			isValid = "your firstName must be character";
			return isValid;
		}else if(user.getLastName()=="" && user.getLastName()==null) {
			isValid = "your lastName can't be empty";
				return isValid;
		}else if(!user.getLastName().matches(regexForName)){
			isValid = "your lastName must be character";
				return isValid;
		}else if(!user.getEmail().matches(regexForEmail)) {
			isValid ="your email is not valid";
			return isValid;
		}else if(!user.getMobNo().matches(regexForMobNo)){
			isValid ="your mob no should be number and 10 digit";
			return isValid;
		}
			else if(user.getPassword()=="" && user.getPassword()==null) {
				isValid = "your password should not be empty";
			return isValid;
		}else if(!user.getPassword().matches(regexForPassword)) {
			isValid = "your password must contain one caps letter,one small letter and min 1 number  ";
			return isValid;
		}else if(!(userService.getUserByEmail(user.getEmail())==null)) {
			isValid = "your email is already registerd:-";
			return isValid;
		}else if(!(userService.getUserByEmail(user.getMobNo())==null)) {
			isValid = "your mobile number is alredy exist";
			return isValid;
		}
		
		return isValid;
	}
	

}
