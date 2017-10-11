package com.bridgelabz.dao;

import com.bridgelabz.model.Notes;

public interface NotesDAO {

	void saveNote(Notes notes);
	boolean deleteNoteById(int id);

}
