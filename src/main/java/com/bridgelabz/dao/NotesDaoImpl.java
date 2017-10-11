package com.bridgelabz.dao;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import com.bridgelabz.model.Notes;

public class NotesDaoImpl implements NotesDAO {

	@Autowired
	SessionFactory sessionFactory;
	Session session=null;
	Transaction transaction = null;
	
	@Override
	public void saveNote(Notes notes) {
		session = sessionFactory.openSession();
		transaction = (Transaction) session.beginTransaction();
		session.save(notes);
	}

	@Override
	public boolean deleteNoteById(int id) {
		session = sessionFactory.openSession();
		transaction = (Transaction)session.beginTransaction();
		Criteria criteria = session.createCriteria(Notes.class);
		criteria.add(Restrictions.eq("id", id));
		Notes notes = (Notes) criteria.uniqueResult();
		session.delete(notes);
		try {
			transaction.commit();
			session.close();
		} catch (SecurityException | RollbackException | HeuristicMixedException | HeuristicRollbackException
				| SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}

}
