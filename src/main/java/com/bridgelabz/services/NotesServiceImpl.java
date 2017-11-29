package com.bridgelabz.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

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

	@Override
	public Notes getNoteById(int noteId) {
		return notesDao.getNoteById(noteId);
	}
	
	@Override
	public List<Notes> getArchivedNotes(User user) {
		List<Notes> notes =notesDao.getArchivedNotes(user);
		return notes;
	}

	@Override
	public List<Notes> getTrashNotes(User user) {
		List<Notes> notes =notesDao.getTrashNotes(user);
		return notes;
	}

	@Override
	public List<Notes> getLabelNotes(String label, User user) {
		return notesDao.getLabelNotes(label, user);
	}

	@Override
	public List<Notes> getReminderedNotes(User user) {
		List<Notes> notes =notesDao.getReminderedNotes(user);
		return notes;
	}

	@Override
	public void deleteTrashedNotes() {
//		System.out.println("checking schedular is working or not inside service method:-");
		notesDao.deleteTrashedNotes();
	}

	/*@Override
	public boolean deleteReminderById(int reminderNoteId) {
		return notesDao.deleteReminderById(reminderNoteId);
	}*/

}
