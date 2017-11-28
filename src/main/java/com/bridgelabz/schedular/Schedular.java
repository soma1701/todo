package com.bridgelabz.schedular;

import org.springframework.scheduling.annotation.Scheduled;
import com.bridgelabz.services.NotesService;

public class Schedular {
	
	NotesService noteService;
	
	int noteId;
	@Scheduled(fixedDelay = 1000)
	public void removeNoteFromTrash() {
		
		noteService.deleteById(noteId);
		System.out.println("note deleted:-");
		
	}

}
