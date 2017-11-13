package com.bridgelabz.controller;

import java.util.Date;
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
import com.bridgelabz.model.Notes;
import com.bridgelabz.model.User;
import com.bridgelabz.services.NotesService;
import com.bridgelabz.validator.NoteValidator;

@RestController
@RequestMapping(value="/notesCredential")
public class NotesDetails {
	private Logger LOG = (Logger) LogManager.getLogger(NotesDetails.class);
	
	@Autowired
	NotesService notesService;
	
	@Autowired
	MyResponse myResponse;
	
	@Autowired
	NoteValidator noteValidation;
	
	@RequestMapping(value="/saveNotes",method=RequestMethod.POST)
	public ResponseEntity<MyResponse> saveNote(@RequestBody Notes notes,HttpSession session,HttpServletRequest request){
		try {
			
			User user = (User) request.getAttribute("user");
			notes.setUser(user);
			Date date = new Date();
			notes.setCreatedTime(date);
			LOG.info("user"+user);
			LOG.info("checking notes details"+notes);
			boolean isNoteValid = noteValidation.noteValidator(notes);
			LOG.info("checking note is valid or not"+isNoteValid);
			if(isNoteValid) {
				LOG.debug("note validation successfull:-");
				LOG.debug("notes"+notes);
				notesService.saveNote(notes);
				myResponse.setResponseMessage("notes save successfully:-");
				return ResponseEntity.ok(myResponse);
			}else {
				LOG.error("your notes validation is not done please enter your notes");
				myResponse.setResponseMessage("your notes can't be null or else not valid:-");
				return new ResponseEntity<MyResponse>(myResponse, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			LOG.debug(e.getMessage());
			myResponse.setResponseMessage("your validation is not done:-");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(myResponse);
			
		}
	}
	
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
	@RequestMapping(value="/getNotes", method=RequestMethod.GET)
	public List<Notes> getNotes(HttpSession session,HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		System.out.println("user " + user);
		//User user = (User)session.getAttribute("userLogin");
		List<Notes>  notes = notesService.getNotes(user);
		System.out.println(notes);
		return notes;
	}
	
	@RequestMapping(value="/editNotes",method=RequestMethod.POST)
	public ResponseEntity<MyResponse> editNotes(@RequestBody Notes note,HttpServletRequest request){
		LOG.info("inside editing notes");
		User user = (User)request.getAttribute("user");
		LOG.info("checking user"+user.getId());
		Notes objNotes = notesService.getNoteById(note.getNotesId());
		note.setUser(user);
		LOG.info("object of notes by id"+objNotes);
		boolean isEdited;
		objNotes.setTitle(note.getTitle());
		objNotes.setDescription(note.getDescription());
		objNotes.setArchived(note.getIsArchived());
		objNotes.setTrashed(note.isTrashed());
		objNotes.setColor(note.getColor());
		Date resetDate = new Date();
		note.setCreatedTime(resetDate);
		isEdited = notesService.editNotes(note);
		LOG.debug("chec`ing edition is done or not"+isEdited);
		if(isEdited){
			myResponse.setResponseMessage("editing notes are successfull");
			return ResponseEntity.ok(myResponse);
		}else
		{
			myResponse.setResponseMessage("edition is not possible");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(myResponse);
		}
	}
	/*@RequestMapping(value="/getArchivedNotes", method=RequestMethod.GET)
	public List<Notes> getReminderedNotes(HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		System.out.println("user " + user);
		//User user = (User)session.getAttribute("userLogin");
		List<Notes>  notes = notesService.getReminderedNotes(user);
		System.out.println(notes);
		return notes;
	}*/
	@RequestMapping(value="/getArchivedNotes", method=RequestMethod.GET)
	public List<Notes> getArchivedNotes(HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		System.out.println("user " + user);
		//User user = (User)session.getAttribute("userLogin");
		List<Notes>  notes = notesService.getArchivedNotes(user);
		System.out.println(notes);
		return notes;
	}
	@RequestMapping(value="/getTrashNotes", method=RequestMethod.GET)
	public List<Notes> getTrashNotes(HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		System.out.println("user " + user);
		//User user = (User)session.getAttribute("userLogin");
		List<Notes>  notes = notesService.getTrashNotes(user);
		System.out.println(notes);
		return notes;
	}
/*	@RequestMapping(value="/deleteFromTrash",method=RequestMethod.PUT)
	public ResponseEntity<MyResponse> deleteFromTrash()
*/
}
