package com.bridgelabz.services;


import com.bridgelabz.model.Token;
import com.bridgelabz.model.User; 

public interface UserService {

	public void register(User user) throws Exception; 
	
	public void sendMail(String from, String to,String Message,String Subject) throws Exception;
	
	public boolean isActivated(int id) throws Exception;

	public User login(User user,String encryptedPassword);
	

	public void resetPassword(String email,String password);

	public void saveTokenInRedis(Token token);

	public Token getToken(String token);



}
