package com.bridgelabz.services;

import java.util.List;

import com.bridgelabz.model.Labels;
import com.bridgelabz.model.Notes;
import com.bridgelabz.model.User;

public interface NotesService {

	void saveNote(Notes notes);

	boolean deleteById(int id);
	
	public  List<Notes> getNotes(User user);

	boolean editNotes(Notes notes);

	public Notes getNoteById(int notesId);

	List<Notes> getArchivedNotes(User user);

	List<Notes> getTrashNotes(User user);

	List<Labels> getLabels(User user);

	void saveNote(Labels labels);

	boolean deleteLabelById(int id);

	/*List<Notes> getReminderedNotes(User user);*/

}
