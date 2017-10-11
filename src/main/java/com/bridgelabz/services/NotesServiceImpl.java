package com.bridgelabz.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.controller.NotesDetails;
import com.bridgelabz.dao.NotesDAO;
import com.bridgelabz.model.Notes;

public class NotesServiceImpl implements NotesService{
	
	@Autowired
	NotesDAO notesDao;
	
	@Override
	public void saveNote(Notes notes, Date notesCreationDate) {
		notesDao.saveNote(notes);
		
	}

	@Override
	public boolean deleteById(int id) {
		notesDao.deleteNoteById(id);
		return true;
	}

}
