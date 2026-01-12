package com.becoder.service;

import java.util.List;

import com.becoder.dto.NotesDto;

public interface NotesService {

<<<<<<< Updated upstream
	public Boolean saveNotes(NotesDto notesDto) throws Exception;
	
	public List<NotesDto> getAllNotes();
=======
	     List<NotesDto> getAllNotes();

		Boolean saveNotes(NotesDto notesDto) throws Exception;

		public NotesResponse getAllNotesByUser(Integer userId, Integer pageNo, Integer pageSize);
		
		public Boolean copyNotes(Integer id) throws Exception;
		
	}

>>>>>>> Stashed changes

}
