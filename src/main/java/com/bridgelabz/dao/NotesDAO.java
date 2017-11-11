package com.bridgelabz.dao;

import java.util.List;
import com.bridgelabz.model.Notes;
import com.bridgelabz.model.User;

public interface NotesDAO {

	void saveNote(Notes notes);
	boolean deleteNoteById(int id);
	public List<Notes> getNotes(User user);
	boolean editNotes(Notes notes);
	Notes getNoteById(int noteId);

}
