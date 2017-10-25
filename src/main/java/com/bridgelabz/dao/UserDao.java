package com.bridgelabz.dao;


import com.bridgelabz.model.Token;
import com.bridgelabz.model.User;

public interface UserDao {
	
	public void register(User user);

	public boolean isActive(int id);

	public User login(User user,String encryptedPassword);

	public void resetPassword(String email,String password);

	public void saveTokenInRedis(Token token);

	public Token getToken(String token);


}
