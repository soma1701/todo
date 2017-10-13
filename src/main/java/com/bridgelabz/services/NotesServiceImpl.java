package com.bridgelabz.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.controller.NotesDetails;
import com.bridgelabz.dao.NotesDAO;
import com.bridgelabz.model.Notes;
import com.bridgelabz.model.User;

public class NotesServiceImpl implements NotesService{
	
	@Autowired
	NotesDAO notesDao;
	
	@Override
	public void saveNote(Notes notes) {
		notesDao.saveNote(notes);
		
	}

	@Override
	public boolean deleteById(int id) {
		notesDao.deleteNoteById(id);
		return true;
	}

	@Override
	public List<Notes> getNotes(User user) {
		List<Notes> notes =notesDao.getNotes(user);
		return notes;
	}

	@Override
	public boolean editNotes(Notes notes) {
		notesDao.editNotes(notes);
		return true;
	}

}
