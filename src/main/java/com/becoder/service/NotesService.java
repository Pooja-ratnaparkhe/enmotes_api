package com.becoder.service;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;


import org.apache.catalina.connector.Response;

import com.becoder.dto.CategoryResponse;

import com.becoder.dto.NotesDto;
import com.becoder.entity.NotesResponse;

public interface NotesService {

	     List<NotesDto> getAllNotes();

		Boolean saveNotes(NotesDto notesDto) throws Exception;

		public NotesResponse getAllNotesByUser(Integer userId, Integer pageNo, Integer pageSize);
		
	// ✅ ADD THIS

    // ✅ ADD THIS
    public Boolean copyNotes(Integer id) throws Exception;
    
    
}

