package com.bridgelabz.validator;

import com.bridgelabz.model.User;

public class RegistrationValidationImpl {

	public boolean validator(User user) {
		boolean isValid = false;
		String regexForEmail = "[a-z0-9]+@[a-z]+.[a-z]{3}";
		String regexForName="[A-Za-z]{2,10}";
		String regexForMobNo="[0-9]{10}";
		String regexForPassword="[A-Za-z0-9]{1,}";
		if(user.getFirstName()=="" && user.getFirstName()==null) {
			System.err.println("your firstName can't be empty");
			return isValid;
		}else if(!user.getFirstName().matches(regexForName)){
			System.err.println("your firstName must be character");
			return isValid;
		}else if(user.getLastName()=="" && user.getLastName()==null) {
				System.err.println("your lastName can't be empty");
				return isValid;
		}else if(!user.getLastName().matches(regexForName)){
				System.err.println("your lastName must be character");
				return isValid;
		}else if(!user.getEmail().matches(regexForEmail)) {
			System.err.println("your email is not valid");
			return isValid;
		}else if(!user.getMobNo().matches(regexForMobNo)){
			System.err.println("your mob no should be number and 10 digit");
			return isValid;
		}
			else if(user.getPassword()=="" && user.getPassword()==null) {
				System.out.println("your password should not be empty");
			return isValid;
		}else if(!user.getPassword().matches(regexForPassword)) {
			System.err.println("your password must contain one caps letter,one small letter and min 1 number  ");
			return isValid;
		} 
		isValid = true;
		return isValid;
	}
	

}
