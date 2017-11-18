package com.bridgelabz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.bridgelabz.dao.UserDao;
import com.bridgelabz.model.Token;
import com.bridgelabz.model.User;

public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	SimpleMailMessage mailMessage;
	
	@Autowired
	MailSender mailSender;

	public void register(User user) throws Exception {
		userDao.register(user);
		
	} 

	public void sendMail(String from, String to, String subject, String msg) {
		mailMessage.setFrom(from);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(msg);
		mailSender.send(mailMessage);
	}

	@Override
	public boolean isActivated(int id) throws Exception {
		return userDao.isActive(id);
	}

	@Override
	public User login(User user,String encryptedPassword) {
		return userDao.login(user,encryptedPassword);
	}

	@Override
	public void resetPassword(String email,String password) {
		userDao.resetPassword(email,password);
	}

	@Override
	public void saveTokenInRedis(Token token) {
		userDao.saveTokenInRedis(token);
	}

	@Override
	public Token getToken(String token) {
		return userDao.getToken(token);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public boolean duplicateEntry(User user) {
		return userDao.dublicateEntry(user);
	}

	/*@Override
	public User getUserByMobNo(String mobNo) {
		return userDao.getUserByMobNo(mobNo);
	}*/

}
