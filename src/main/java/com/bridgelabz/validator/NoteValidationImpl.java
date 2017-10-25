package com.bridgelabz.validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.bridgelabz.model.Notes;

public class NoteValidationImpl implements NoteValidator {
	private Logger LOG = (Logger) LogManager.getLogger(NoteValidator.class);
	@Override
	public boolean noteValidator(Notes note) {
		boolean isNoteValid=false;
		if(note.getTitle()==""||note.getTitle()==null) {
			LOG.debug("your note's title can't be empty:-");
			return isNoteValid;
		}else if(note.getDescription()==""||note.getTitle()==null) {
			LOG.debug("your notes's description can't be empty:-");
			return isNoteValid;
		}
		isNoteValid=true;
		return isNoteValid;
	}

}
