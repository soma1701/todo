package com.bridgelabz.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.model.Labels;
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
		Query query = session.createSQLQuery("select {note.*}\n" + 
				"from user as user\n" + 
				"join note_user as jt on (user.id = jt.id)\n" + 
				"join soma.notes as note on (jt.note_id = note.notes_id)\n" + 
				"where user.email = :email").addEntity("note",Notes.class);
		List<Notes> notes= query.setString("email", user.getEmail()).list();
		return notes;
	}

	@Override
	public boolean editNotes(Notes notes) {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		try {
			session.update(notes);
			transaction.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			if(transaction!= null) {
				transaction.rollback();
				session.close();
				return false;
			}
			e.printStackTrace();
		}
		return true;
		
		
		
	}

	@Override
	public Notes getNoteById(int noteId) {
		Notes objNote = new Notes();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		try {
			objNote = (Notes) session.get(Notes.class,noteId);
			transaction.commit();
			session.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			if(transaction!= null) {
				transaction.rollback();
				session.close();
				return null;
			}
			e.printStackTrace();
		}
		return objNote;
	}

	@Override
	public List<Notes> getArchivedNotes(User user) {
		session = sessionFactory.openSession();
		transaction = (Transaction) session.beginTransaction();
		Criteria criteria = session.createCriteria(Notes.class);
		criteria.add(Restrictions.eqOrIsNull("user", user));
		criteria.add(Restrictions.eq("isArchived", true));
		List<Notes> notes= criteria.list();
		return notes;
	}

	@Override
	public List<Notes> getTrashNotes(User user) {
		session = sessionFactory.openSession();
		transaction = (Transaction) session.beginTransaction();
		Criteria criteria = session.createCriteria(Notes.class);
		criteria.add(Restrictions.eqOrIsNull("user", user));
		criteria.add(Restrictions.eq("isTrashed", true));
		List<Notes> notes= criteria.list();
		return notes;
	}

	@Override
	public Set<Notes> getLabelNotes(String label, User user) {
		session = sessionFactory.openSession();
		transaction = (Transaction) session.beginTransaction();
		Criteria criteria = session.createCriteria(Labels.class);
		criteria.add(Restrictions.eqOrIsNull("user", user));
		criteria.add(Restrictions.eq("labelName", label));
		Labels objLabel = (Labels) criteria.uniqueResult();
		Set<Notes> notes= objLabel.getAlNote();
		return notes;
	}

	/*@Override
	public List<Notes> getReminderedNotes(User user) {
		session = sessionFactory.openSession();
		transaction = (Transaction) session.beginTransaction();
		Criteria criteria = session.createCriteria(Notes.class);
		criteria.add(Restrictions.eqOrIsNull("user", user));
		criteria.add(Restrictions.eq("isTrashed", true));
		List<Notes> notes= criteria.list();
		return notes;
	}*/

}
