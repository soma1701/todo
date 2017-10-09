package com.bridgelabz.validator;

import com.bridgelabz.model.User;

public class RegistrationValidationImpl {

	public boolean validator(User user) {
		boolean isValid = false;
		String regexForEmail = "/^[a-z0-9]+@[a-z]+.[a-z]{3}/";
		String regexForName="[A-Za-z]";
		String regexForMobNo="[0-9]{11}";
		String regexForPassword="[A-Za-z0-9]{3}";
		
		if(user.getFirstName()=="") {
			System.err.println("your firstName can't be empty");
			return isValid;
		}else if(regexForName.matches(user.getFirstName())){
			System.err.println("your firstName must be character");
			return isValid;
		}else if(user.getLastName()=="") {
				System.err.println("your lastName can't be empty");
				return isValid;
		}else if(regexForName.matches(user.getLastName())){
			
				System.err.println("your lastName must be character");
				return isValid;
		}else if(regexForEmail.matches(user.getEmail())) {
			System.err.println("your email is not valid");
			return isValid;
		}else if(regexForMobNo.matches(user.getMobNo())){
			System.err.println("your mob no should be number and 10 digit");
			return isValid;
		}
			else if(user.getPassword()=="") {
				System.out.println("your password should not be empty");
			
		}else if(regexForPassword.matches(user.getPassword())) {
			System.err.println("your password ");
			return isValid;
		} 
		isValid = true;
		return isValid;
	}
	

}
