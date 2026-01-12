package com.becoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.dto.NotesDto;
import com.becoder.service.NotesService;
import com.becoder.util.CommonUtil;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

	@Autowired
	private NotesService notesService;

<<<<<<< Updated upstream
	@PostMapping("/")
	public ResponseEntity<?> saveNotes(@RequestBody NotesDto notesDto) throws Exception {
=======
	@PostMapping
	public ResponseEntity<?> saveNotes(@RequestBody NotesDto notesDto) throws Exception 
	{
>>>>>>> Stashed changes
		Boolean saveNotes = notesService.saveNotes(notesDto);
		
		if (saveNotes) 
		{
			return CommonUtil.createBuildResponseMessage("Notes saved success", HttpStatus.CREATED);
		}
		
		return CommonUtil.createErrorResponseMessage("Notes not saved", HttpStatus.INTERNAL_SERVER_ERROR);
	}

<<<<<<< Updated upstream
	@GetMapping("/")
	public ResponseEntity<?> getAllNotes() {
=======
	
	//get all data 
	@GetMapping
	public ResponseEntity<?> getAllNotes() 
	{
>>>>>>> Stashed changes
		List<NotesDto> notes = notesService.getAllNotes();
		
		if (CollectionUtils.isEmpty(notes)) 
		{
			return ResponseEntity.noContent().build();
		}
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}
<<<<<<< Updated upstream
=======
	
	
	//pagination get data
	@GetMapping("/user-notes")
	public ResponseEntity<?> getAllNotesByUser(@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
			
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize)
	{
		Integer userId = 2;
		
		NotesResponse notes = notesService.getAllNotesByUser(userId,pageNo,pageSize);
	
		return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
	}
>>>>>>> Stashed changes

	
	// copy notes(duplicate)
	
	 @PostMapping("/copy/{id}")
    public ResponseEntity<?> copyNotes(@PathVariable Integer id) throws Exception {

        Boolean copied = notesService.copyNotes(id);

        if (copied) {
            return CommonUtil.createBuildResponseMessage(
                "Notes copied successfully",
                HttpStatus.CREATED
            );
        }

        return CommonUtil.createErrorResponseMessage(
            "Copy failed",
            HttpStatus.INTERNAL_SERVER_ERROR
        );
	 }
}
