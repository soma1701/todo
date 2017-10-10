package com.bridgelabz.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.bridgelabz.model.Token;
import com.bridgelabz.model.User;

public class UserDaoImpl implements UserDao {
	
	private static final String key="Token";

	@Autowired
	private RedisTemplate<String, Object> template;
	
	private HashOperations<String, String, Token> hashops;
	
	
	@Autowired
	SessionFactory sessionFactory;
	

	Session session = null;
	Transaction tx = null;

	public void register(User user) {
		session = sessionFactory.openSession();
		session = sessionFactory.openSession();
		tx = (Transaction) session.beginTransaction();
		session.save(user);
		try {
			tx.commit();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		session.close();
	}

	@Override
	public void isActive(int id) {
		session = sessionFactory.openSession();
		tx = (Transaction) session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("id", id));
		User user = (User) cr.uniqueResult();
		user.setValid(true);
		tx.commit();
		session.close();
	}

	@Override
	public User login(User user) {
		System.out.println("Email: " + user.getEmail() + " Password: " + user.getPassword());
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("email", user.getEmail()));
		cr.add(Restrictions.eq("password", user.getPassword()));
		cr.add(Restrictions.eq("isValid", true));
		User userLogin = (User) cr.uniqueResult();
		if (userLogin == null) {
			System.out.println("logged in user " + userLogin);
			return userLogin;
		} else {
			return userLogin;

		}
	}
	@Override
	public void resetPassword(String email, String password) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("email", email));
		User userResetPassword = (User) cr.uniqueResult();
		userResetPassword.setPassword(password);
		session.update(userResetPassword);
		tx.commit();
		session.close();
	}

	@Override
	public void saveTokenInRedis(Token token) {
		hashops = template.opsForHash();
		hashops.put(key, token.getGenerateToken(), token);
		System.out.println("is this null " +hashops.get(key, token.getGenerateToken()));
		
		
	}

	@Override
	public Token getToken(String token) {
		hashops = template.opsForHash();
		Token token2 = hashops.get(key, token);
		return token2;
		
	}

}
