package com.bridgelabz.services;

import java.util.Date;

import com.bridgelabz.controller.NotesDetails;
import com.bridgelabz.model.Notes;

public interface NotesService {

	void saveNote(Notes notes,Date date);

	boolean deleteById(int id);

}
