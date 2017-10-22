package com.bridgelabz.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.model.MyResponse;
import com.bridgelabz.model.Notes;
import com.bridgelabz.model.User;
import com.bridgelabz.services.NotesService;

@RestController
public class NotesDetails {
	 private final Logger LOG = LoggerFactory.getLogger(NotesDetails.class);
	
	@Autowired
	NotesService notesService;
	
	@Autowired
	MyResponse myResponse;
	
	@RequestMapping(value="/saveNotes",method=RequestMethod.POST)
	public ResponseEntity<MyResponse> saveNote(@RequestBody Notes notes,HttpSession session){
		
		User user =(User) session.getAttribute("userLogin");
		notes.setUser(user);
		Date date = new Date();
		notes.setCreatedTime(date);
		notesService.saveNote(notes);
		LOG.debug("notes save successfully");
		myResponse.setResponseMessage("notes save successfully");
		return ResponseEntity.ok(myResponse);
	}
	
	@RequestMapping(value="/deleteNotes/{id}", method=RequestMethod.POST)
	public ResponseEntity<MyResponse> deleteNotes(@PathVariable int id){
		boolean isDeleted = notesService.deleteById(id);
		if(isDeleted) {
			myResponse.setResponseMessage("deleted successfully");
			return ResponseEntity.ok(myResponse);
		}
		else {
			myResponse.setResponseMessage("unable to delete");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
		}
		
		
	}
	@RequestMapping(value="/getNotes", method=RequestMethod.GET)
	public List<Notes> getNotes(HttpSession session){
		User user = (User)session.getAttribute("userLogin");
		List<Notes>  notes = notesService.getNotes(user);
		return notes;
		
	}
	
	@RequestMapping(value="/editNotes",method=RequestMethod.PUT)
	public ResponseEntity<MyResponse> editNotes(@RequestBody Notes notes,HttpSession session){
		Notes objNotes = notesService.getNoteById(notes.getNotesId());
		User user = (User)session.getAttribute("userLogin");
		boolean isEdited;
		notes.setUser(user);
		notes.setTitle(notes.getTitle());
		notes.setDescription(notes.getDescription());
		Date resetDate = new Date();
		notes.setCreatedTime(resetDate);
		isEdited = notesService.editNotes(notes);
		if(isEdited){
			myResponse.setResponseMessage("editing notes are successfull");
			return ResponseEntity.ok(myResponse);
		}else
		{
			myResponse.setResponseMessage("edition is not possible");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(myResponse);
			
		}
		
		
	}

}
