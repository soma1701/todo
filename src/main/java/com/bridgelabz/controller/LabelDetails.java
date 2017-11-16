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

import com.bridgelabz.model.Labels;
import com.bridgelabz.model.MyResponse;
import com.bridgelabz.model.Notes;
import com.bridgelabz.model.User;
import com.bridgelabz.services.LabelService;
import com.bridgelabz.services.NotesService;
import com.bridgelabz.validator.NoteValidator;

@RestController
@RequestMapping(value="/LabelsCredential")
public class LabelDetails {

	private Logger LOG = (Logger) LogManager.getLogger(NotesDetails.class);
	
	@Autowired
	LabelService labelService;
	
	@Autowired
	MyResponse myResponse;
	
	
	@RequestMapping(value="/saveLabel",method=RequestMethod.POST)
	public ResponseEntity<MyResponse> saveLabel(@RequestBody Labels labels,HttpSession session,HttpServletRequest request){
		try {
			
			User user = (User) request.getAttribute("user");
			labels.setUser(user);
			Date date = new Date();
			labels.setCreatedTime(date);
			LOG.info("user"+user);
			LOG.info("checking notes details"+labels);
				labelService.saveLabel(labels);
				myResponse.setResponseMessage("notes save successfully:-");
				return ResponseEntity.ok(myResponse);
				} catch (Exception e) {
			LOG.debug(e.getMessage());
			myResponse.setResponseMessage("your validation is not done:-");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(myResponse);
			
		}
	}
	
	@RequestMapping(value="/deleteLabels/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<MyResponse> deleteLabel(@PathVariable int id){
		boolean isDeleted = labelService.deleteLabelById(id);
		if(isDeleted) {
			myResponse.setResponseMessage("deleted successfully");
			return ResponseEntity.ok(myResponse);
		}
		else {
			myResponse.setResponseMessage("unable to delete");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myResponse);
		}
		
		
	}
	@RequestMapping(value="/getLabels", method=RequestMethod.GET)
	public List<Labels> getLabels(HttpSession session,HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		System.out.println("user " + user);
		//User user = (User)session.getAttribute("userLogin");
		List<Labels>  labels = labelService.getLabels(user);
		System.out.println(labels);
		return labels;
	}
	
	@RequestMapping(value="/editLabel",method=RequestMethod.POST)
	public ResponseEntity<MyResponse> editNotes(@RequestBody Labels label,HttpServletRequest request){
		LOG.info("inside editing notes");
		User user = (User)request.getAttribute("user");
		LOG.info("checking user"+user.getId());
		Labels objLabel = labelService.getLabelById(label.getLabelId());
		label.setUser(user);
		LOG.info("object of notes by id"+objLabel);
		boolean isEdited;
		Date resetDate = new Date();
		isEdited = labelService.editLabel(label);
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

}
