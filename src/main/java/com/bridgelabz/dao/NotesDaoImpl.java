package com.bridgelabz.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import com.bridgelabz.model.Notes;
import com.bridgelabz.model.User;

public class NotesDaoImpl implements NotesDAO {

	@Autowired
	SessionFactory sessionFactory;
	Session session=null;
	Transaction transaction = null;
	
	@Override
	public void saveNote(Notes notes) {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		try {
			session.save(notes);
			transaction.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean deleteNoteById(int id) {
		session = sessionFactory.openSession();
		transaction = (Transaction)session.beginTransaction();
		Criteria criteria = session.createCriteria(Notes.class);
		criteria.add(Restrictions.eq("id", id));
		Notes notes = (Notes) criteria.uniqueResult();
		
		try {
			session.delete(notes);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
		
	}

	@Override
	public List<Notes> getNotes(User user) {
		session = sessionFactory.openSession();
		transaction = (Transaction) session.beginTransaction();
		Criteria criteria = session.createCriteria(Notes.class);
		criteria.add(Restrictions.eqOrIsNull("user", user));
		List<Notes> notes= criteria.list();
		return notes;
	}

	@Override
	public boolean editNotes(Notes notes) {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		try {
			session.update(notes);
			transaction.commit();
			
		} catch (Exception e) {
			if(transaction!= null) {
				transaction.rollback();
				return false;
			}
			e.printStackTrace();
		}
		return true;
		
		
		
	}

}
