package com.bridgelabz.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.controller.NotesDetails;
import com.bridgelabz.dao.NotesDAO;

public class NotesServiceImpl implements NotesService{
	
	@Autowired
	NotesDAO notesDao;
	
	@Override
	public void saveNote(NotesDetails notesDetails) {
		notesDao.saveNote(notesDetails);
		
	}

}
