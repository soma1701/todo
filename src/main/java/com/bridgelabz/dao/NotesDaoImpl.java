package com.bridgelabz.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.model.NoteUser;
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
				"join notes as note on (jt.note_id = note.notes_id)\n" + 
				"where user.email = :email").addEntity("note",Notes.class);
		List<Notes> notes= query.setString("email", user.getEmail()).list();
		return notes;
	}

	@Override
	public boolean editNotes(Notes notes) {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		try {
			session.saveOrUpdate(notes);
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
			objNote.getNoteUser();
			for(NoteUser objNoteUSer : objNote.getNoteUser()) {
				objNoteUSer.getNote();
				objNoteUSer.getUser();
			}
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
		Query query = session.createSQLQuery("select {note.*}\n" + 
				"from user as user\n" + 
				"join note_user as jt on (user.id = jt.id)\n" + 
				"join notes as note on (jt.note_id = note.notes_id)\n" + 
				"where user.id = :id and note.is_archive=b'1'").addEntity("note",Notes.class);
		List<Notes> notes= query.setInteger("id", user.getId()).list();
		return notes;
	}

	@Override
	public List<Notes> getTrashNotes(User user) {
		session = sessionFactory.openSession();
		transaction = (Transaction) session.beginTransaction();
		Query query = session.createSQLQuery("select {note.*}\n" + 
				"from user as user\n" + 
				"join note_user as jt on (user.id = jt.id)\n" + 
				"join notes as note on (jt.note_id = note.notes_id)\n" + 
				"where user.id = :id and note.is_trashed=b'1'").addEntity("note",Notes.class);
		List<Notes> notes= query.setInteger("id", user.getId()).list();
		return notes;
	}

	@Override
	public List<Notes> getLabelNotes(String label, User user) {
		session = sessionFactory.openSession();
		transaction = (Transaction) session.beginTransaction();
		Query query = session.createSQLQuery("select {note.*}\n" + 
				"from user as user\n" + 
				"join note_user as nu on (user.id = nu.id)\n" + 
				"join notes as note on (nu.note_id = note.notes_id)\n" + 
				"join note_label as nl on (nl.note_id = note.notes_id)\n" + 
				"join labels as label on (label.label_id = nl.label_id)\n" + 
				"where user.id = :userId and label.label_name= :labelName").addEntity("note",Notes.class);
		List<Notes> notes= query.setInteger("userId", user.getId()).setString("labelName", label).list();
		return notes;
	}

	@Override
	public List<Notes> getReminderedNotes(User user) {
		session = sessionFactory.openSession();
		transaction = (Transaction) session.beginTransaction();
		Query query = session.createSQLQuery("select {note.*}\n" + 
				"from user as user\n" + 
				"join note_user as jt on (user.id = jt.id)\n" + 
				"join notes as note on (jt.note_id = note.notes_id)\n" + 
				"where user.id = :id and note.reminder is not null").addEntity("note",Notes.class);
		List<Notes> notes= query.setInteger("id", user.getId()).list();
		return notes;
	}

	
	public void deleteTrashedNotes() {
		session = sessionFactory.openSession();
		try {
			transaction = session.beginTransaction();
			String hql = "delete from Notes where isTrashed = :isTrashed AND createdTime < :createdTime";
			Query query = session.createQuery(hql);
			Date date = new Date();
			date = new Date(date.getTime() - 60000);
			query.setParameter("isTrashed", true);
			query.setParameter("createdTime", date);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
