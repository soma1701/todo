package com.bridgelabz.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.model.MyErrorMessage;
import com.bridgelabz.services.NotesService;

@RestController
public class NotesDetails {
	
	NotesService notesService;
	
	@Autowired
	MyErrorMessage errorMessage;
	
	@RequestMapping(value="/saveNotes",method=RequestMethod.POST)
	public ResponseEntity<MyErrorMessage> saveNote(@RequestBody NotesDetails notesDetails, HttpServletRequest request){
		Date date = new Date();
		int notesCreationDate = date.getDate();
		notesService.saveNote(notesDetails,int notesCreationDate);
		
		errorMessage.setErrorMessage("notes save successfully");
		return ResponseEntity.ok(errorMessage);
		
	}

}
