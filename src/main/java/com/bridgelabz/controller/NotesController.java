package com.bridgelabz.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.model.MyResponse;
import com.bridgelabz.model.NoteUser;
import com.bridgelabz.model.Notes;
import com.bridgelabz.model.User;
import com.bridgelabz.services.NotesService;
import com.bridgelabz.services.UserService;
import com.bridgelabz.validator.NoteValidator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *  @author Soma Singh
 * @see class for Note Related task
 */
@RestController
@RequestMapping(value="/notesCredential")
public class NotesController {
	private Logger LOG = (Logger) LogManager.getLogger(NotesController.class);
	
	@Autowired
	NotesService notesService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MyResponse myResponse;
	
	@Autowired
	NoteValidator noteValidation;
	
	/**
	 * @param note	
	 * @param request
	 * @return MyResponse
	 * @see this method is for saving notes in mysql db
	 */
	@RequestMapping(value="/saveNotes",method=RequestMethod.POST)
	public ResponseEntity<MyResponse> saveNote(@RequestBody Notes note,HttpSession session,HttpServletRequest request){
		try {
			
			User user = (User) request.getAttribute("user");
			NoteUser objNoteUser = new NoteUser();
			objNoteUser.setNote(note);
			objNoteUser.setUser(user);
			objNoteUser.setOwner(true);
			note.getNoteUser().add(objNoteUser);
			Date date = new Date();
			note.setCreatedTime(date);
			String isNoteValid = noteValidation.noteValidator(note);
			if(isNoteValid.equals("true")) {
				notesService.saveNote(note);
				myResponse.setResponseMessage("notes save successfully:-");
				return ResponseEntity.ok(myResponse);
			}else {
				myResponse.setResponseMessage("your notes can't be null or else not valid:-");
				return new ResponseEntity<MyResponse>(myResponse, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			LOG.debug(e.getMessage());
			myResponse.setResponseMessage("your validation is not done:-");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(myResponse);
			
		}
	}

	/**
	 * @param note	
	 * @param request
	 * @return MyResponse
	 * @see this method is for deleting notes
	 */
	@RequestMapping(value="/deleteNotes/{id}", method=RequestMethod.DELETE)
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
	/**
	 * @param note	
	 * @param request
	 * @return MyResponse
	 * @see this method is for geting notes
	 */
	@RequestMapping(value="/getNotes", method=RequestMethod.GET)
	public List<Notes> getNotes(HttpSession session,HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		User objUser = userService.getUserById(user.getId());
		List<Notes>  notes = notesService.getNotes(objUser);
		return notes;
	}
	/**
	 * @param note	
	 * @param request
	 * @return MyResponse
	 * @see this method is for editing notes
	 */
	@RequestMapping(value="/editNotes",method=RequestMethod.POST)
	public ResponseEntity<MyResponse> editNotes(@RequestBody Notes notes,HttpServletRequest request){
//		User user = (User)request.getAttribute("user");
//		Notes objNotes = notesService.getNoteById(notes.getNotesId());
//		note.getUser().add(user);
//		User user = userService.getUserByEmail("souravpoddar1992@gmail.com");
		boolean isEdited;
		for(NoteUser objNoteUser : notes.getNoteUser()) {
			objNoteUser.setNote(notes );
		}
/*		objNotes.setNoteUser(note.getNoteUser());
		objNotes.setTitle(note.getTitle());
		objNotes.setDescription(note.getDescription());
		objNotes.setArchived(note.getIsArchived());
		objNotes.setTrashed(note.isTrashed());
		objNotes.setColor(note.getColor());
		objNotes.setLabels(note.getLabels());
		objNotes.setImage(note.getImage());
		Date resetDate = new Date();
		note.setCreatedTime(resetDate);*/
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
	/**
	 * @param note	
	 * @param request
	 * @return MyResponse
	 * @see this method is for geting archive notes
	 */
	@RequestMapping(value="/getArchivedNotes", method=RequestMethod.GET)
	public List<Notes> getArchivedNotes(HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		List<Notes>  notes = notesService.getArchivedNotes(user);
		return notes;
	}
	/**
	 * @param note	
	 * @param request
	 * @return MyResponse
	 * @see this method is for geting all trashed notes
	 */
	@RequestMapping(value="/getTrashNotes", method=RequestMethod.GET)
	public List<Notes> getTrashNotes(HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		System.out.println("user " + user);
		List<Notes>  notes = notesService.getTrashNotes(user);
		return notes;
	}
	
	/**
	 * @param label	
	 * @param request
	 * @return MyResponse
	 * @see this method is for geting labels
	 */
	@RequestMapping(value="getLabelNotes/{label}", method=RequestMethod.GET)
	public List<Notes> getLabels(@PathVariable String label,HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		List<Notes> alNotes = notesService.getLabelNotes(label, user);
		return alNotes;
	}
	
	@RequestMapping(value="/shareNote", method=RequestMethod.POST)
	public ResponseEntity<MyResponse> shareNote(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException{
//		User currentUser = (User) request.getAttribute("user");
		String noteString = request.getParameter("note");
		String email = request.getParameter("email");
		User user = userService.getUserByEmail(email);
		Notes objNote = new ObjectMapper().readValue(noteString, Notes.class);
		for(NoteUser objNoteUser : objNote.getNoteUser()) {
			objNoteUser.setNote(objNote);
		}
		NoteUser objNoteUser = new NoteUser();
		objNoteUser.setNote(objNote);
		objNoteUser.setUser(user);
		objNote.getNoteUser().add(objNoteUser);
		if(notesService.editNotes(objNote)){
			myResponse.setResponseMessage("editing notes are successfull");
			return ResponseEntity.ok(myResponse);
		}else
		{
			myResponse.setResponseMessage("edition is not possible");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(myResponse);
		}
	}
	
	@RequestMapping(value="/removeSharing", method=RequestMethod.POST)
	public ResponseEntity<MyResponse> removingSharing(HttpServletRequest request){
		String noteId = request.getParameter("noteId");
		String email = request.getParameter("userEmail");
		Notes objNotes = notesService.getNoteById(Integer.parseInt(noteId));
		boolean isEdited;
		Iterator<NoteUser> it = objNotes.getNoteUser().iterator();
		while(it.hasNext()){
			NoteUser objNoteUser = it.next();
			if(email.equalsIgnoreCase(objNoteUser.getUser().getEmail())){
				objNotes.getNoteUser().remove(objNoteUser);
			}
			objNoteUser.setNote(objNotes );
		}
		isEdited = notesService.editNotes(objNotes);
		if(isEdited){
			myResponse.setResponseMessage("editing notes are successfull");
			return ResponseEntity.ok(myResponse);
		}else
		{
			myResponse.setResponseMessage("edition is not possible");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(myResponse);
		}
	}
	
	@RequestMapping(value="/getReminderNotes", method=RequestMethod.GET)
	public List<Notes> getReminderNotes(HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		System.out.println("user " + user);
		List<Notes>  notes = notesService.getReminderedNotes(user);
		return notes;
	}
	/*@RequestMapping(value="/deleteNotes/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<MyResponse> deleteReminder(@PathVariable int reminderNoteId){
		boolean isDeleted = notesService.deleteReminderById(reminderNoteId);
		if(isDeleted) {
			myResponse.setResponseMessage("deleted successfully");
			return ResponseEntity.ok(myResponse);
		}
		else {
			myResponse.setResponseMessage("unable to delete");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
		}
	}*/
}
