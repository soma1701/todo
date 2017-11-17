package com.bridgelabz.validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.model.MyResponse;
import com.bridgelabz.model.Notes;

public class NoteValidationImpl implements NoteValidator {
	
	private Logger LOG = (Logger) LogManager.getLogger(NoteValidationImpl.class);
	
	@Autowired
	MyResponse myResponse;
	
	@Override
	public String noteValidator(Notes note) {
		String isNoteValid="false";
		if((note.getTitle()==""||note.getTitle()==null) && (note.getDescription()==""||note.getDescription()==null)) {
			myResponse.setResponseMessage("you shoud have enter atlist one field either title or description:-");
			return isNoteValid;
		}/*else if(note.getDescription()==""||note.getTitle()==null) {
			LOG.debug("your notes's description can't be empty:-");
			return isNoteValid;
		}*/
		
		isNoteValid="true";
		return isNoteValid;
	}

}
