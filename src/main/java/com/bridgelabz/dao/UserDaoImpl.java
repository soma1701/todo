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
	public boolean isActive(int id) {
		session = sessionFactory.openSession();
		tx = (Transaction) session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("id", id));
		User user = (User) cr.uniqueResult();
		boolean isValid;
		if(user==null) {
			isValid= false;
			return isValid;
		}else {
			isValid = true;
			user.setValid(true);
			tx.commit();
			session.close();
		}
		return isValid;
	}

	@Override
	public User login(User user,String encryptedPassword) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("email", user.getEmail()));
		cr.add(Restrictions.eq("password", encryptedPassword));
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

	@Override
	public User getUserByEmail(String email) {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("email", email));
		User userByEmail=(User)cr.uniqueResult();
		return userByEmail;
	}

	@Override
	public boolean dublicateEntry(User user) {
		boolean isExistUser=false;;
		tx = session.beginTransaction();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("email", user.getEmail()));
		User userByEmail=(User)cr.uniqueResult();
		if(userByEmail==null) {
			session.save(userByEmail);
			return isExistUser;
		}else {
			isExistUser=true;
			return isExistUser;
		}
	}

}
