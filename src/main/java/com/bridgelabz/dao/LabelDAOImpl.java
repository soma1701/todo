package com.bridgelabz.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import com.bridgelabz.model.Labels;
import com.bridgelabz.model.User;

public class LabelDAOImpl implements LabelDAO {

	@Autowired
	SessionFactory sessionFactory;
	Session session = null;
	Transaction transaction = null;

	@Override
	public void saveLabel(Labels label) {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		try {
			session.save(label);
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(int id) {
		session = sessionFactory.openSession();
		transaction = (Transaction) session.beginTransaction();
		Criteria criteria = session.createCriteria(Labels.class);
		criteria.add(Restrictions.eq("id", id));
		Labels labels = (Labels) criteria.uniqueResult();

		try {
			session.delete(labels);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Labels> getLabels(User user) {
		session = sessionFactory.openSession();
//		transaction = (Transaction) session.beginTransaction();
		Criteria criteria = session.createCriteria(Labels.class);
		criteria.add(Restrictions.eqOrIsNull("user", user));
		List<Labels> labels = criteria.list();
		return labels;
	}

	@Override
	public Labels getLabelById(final int labelId) {
		Labels objLabel = null;
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		try {
			objLabel = (Labels) session.get(Labels.class, labelId);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
				session.close();
				return null;
			}
			e.printStackTrace();
		}
		return objLabel;
	}

	@Override
	public boolean editLabel(Labels label) {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		try {
			session.update(label);
			transaction.commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
				session.close();
				return false;
			}
			e.printStackTrace();
		}
		return true;

	}

	@Override
	public Labels getLabelByName(String labelName) {
		Labels objLabel = null;
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Labels.class);
		criteria.add(Restrictions.eq("labelName", labelName));
		objLabel = (Labels) criteria.uniqueResult();
		session.close();
		return objLabel;
	}

}
