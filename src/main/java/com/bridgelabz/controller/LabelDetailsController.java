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

/**
 * @author Soma Singh
 * @see class for user related task
 */
@RestController
@RequestMapping(value="/LabelsCredential")
public class LabelDetailsController {
	private Logger LOG = (Logger) LogManager.getLogger(NotesController.class);
	
	@Autowired
	LabelService labelService;
	
	@Autowired
	MyResponse myResponse;
	
	/**
	 * @param label	
	 * @param request
	 * @return MyResponse
	 * @see this method is for creating label
	 */
	@RequestMapping(value="/saveLabel",method=RequestMethod.POST)
	public ResponseEntity<MyResponse> saveLabel(@RequestBody Labels labels,HttpSession session,HttpServletRequest request){
		try {
			User user = (User) request.getAttribute("user");
			labels.setUser(user);
			Date date = new Date();
			labels.setCreatedTime(date);
				labelService.saveLabel(labels);
				myResponse.setResponseMessage("label save successfully:-");
				return ResponseEntity.ok(myResponse);
				} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(myResponse);
		}
	}

	/**
	 * @param label
	 * @param request
	 * @return MyResponse
	 * @see this method is for deleting labels
	 */
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
	/**
	 * @param label	
	 * @param request
	 * @return MyResponse
	 * @see this method is for geting labels
	 */
	@RequestMapping(value="/getLabels", method=RequestMethod.GET)
	public List<Labels> getLabels(HttpSession session,HttpServletRequest request){
		User user = (User) request.getAttribute("user");
		List<Labels>  labels = labelService.getLabels(user);
		return labels;
	}
	/**
	 * @param label	
	 * @param request
	 * @return MyResponse
	 * @see this method is for editng labels
	 */
	@RequestMapping(value="/editLabel",method=RequestMethod.POST)
	public ResponseEntity<MyResponse> editNotes(@RequestBody Labels label,HttpServletRequest request){
		User user = (User)request.getAttribute("user");
		Labels objLabel = labelService.getLabelById(label.getLabelId());
		label.setUser(user);
		boolean isEdited;
		Date resetDate = new Date();
		isEdited = labelService.editLabel(label);
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
