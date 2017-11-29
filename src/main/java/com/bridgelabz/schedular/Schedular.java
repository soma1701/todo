package com.bridgelabz.schedular;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.services.NotesService;

public class Schedular {

	@Autowired
	NotesService noteService;

	public void deleteTrashedNotes() {
		noteService.deleteTrashedNotes();
	}

}
