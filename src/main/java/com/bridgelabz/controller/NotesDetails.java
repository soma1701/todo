package com.bridgelabz.controller;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.model.MyResponse;
import com.bridgelabz.model.Notes;
import com.bridgelabz.services.NotesService;

@RestController
public class NotesDetails {
	
	@Autowired
	NotesService notesService;
	
	@Autowired
	MyResponse myResponse;
	
	@RequestMapping(value="/saveNotes",method=RequestMethod.POST)
	public ResponseEntity<MyResponse> saveNote(@RequestBody Notes notes){
		Date date = new Date();
		int notesCreationDate = date.getDate();
		notesService.saveNote(notes,date);
		myResponse.setResponseMessage("notes save successfully");
		return ResponseEntity.ok(myResponse);
	}
	
	@RequestMapping(value="/deletNotes{id}")
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
	@RequestMapping(value="/editNoted")
	public ResponseEntity<MyResponse> editNotes(@RequestBody Notes notes){
		return ResponseEntity
		
	}

}
