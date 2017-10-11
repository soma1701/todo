package com.bridgelabz.dao;

import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.controller.NotesDetails;

public class NotesDaoImpl implements NotesDAO {

	@Autowired
	SessionFactory sessionFactory;
	Session session=null;
	Transaction transaction = null;
	
	@Override
	public void saveNote(NotesDetails notesDetails) {
		session = sessionFactory.openSession();
		transaction = (Transaction) session.beginTransaction();
		session.save(notesDetails);
	}

}
