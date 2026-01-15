package com.becoder.service.impl;

import java.awt.print.Pageable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import com.becoder.dto.NotesDto;
import com.becoder.dto.NotesDto.CategoryDto;
import com.becoder.entity.Notes;
import com.becoder.entity.NotesResponse;
import com.becoder.exception.ResourceNotFoundException;
import com.becoder.repository.CategoryRepository;
import com.becoder.repository.NotesRepository;
import com.becoder.service.NotesService;


@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesRepository notesRepo;

    @Autowired
    private ModelMapper mapper;



//	@Autowired
//	private CategoryRepository categoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

   


	@Override
	public NotesResponse getAllNotesByUser(Integer userId, Integer pageNo, Integer pageSize) {
		
		// 10 = 5,5 = 2 pages
		PageRequest pageable = PageRequest.of(pageNo, pageSize);
		Page<Notes> pageNotes = notesRepo.findByCreatedBy(userId, pageable);

		List<NotesDto> notesDto = pageNotes.get().map(n -> mapper.map(n, NotesDto.class)).toList();

		NotesResponse notes = NotesResponse.builder().notes(notesDto).pageNo(pageNotes.getNumber())
				.pageSize(pageNotes.getSize()).totalElements(pageNotes.getTotalElements())
				.totalPages(pageNotes.getTotalPages()).isFirst(pageNotes.isFirst()).isLast(pageNotes.isLast()).build();

				
		return notes;
	}




    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public Boolean saveNotes(NotesDto notesDto) throws Exception {

        // category validation
        checkCategoryExist(notesDto.getCategory());

        Notes notes = mapper.map(notesDto, Notes.class);
        Notes saveNotes = notesRepo.save(notes);

        return !ObjectUtils.isEmpty(saveNotes);
    }

    private void checkCategoryExist(CategoryDto category) throws Exception {
        categoryRepo.findById(category.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Category id invalid"));
    }


    @Override
    public List<NotesDto> getAllNotes() {
        return notesRepo.findAll()
                .stream()
                .map(note -> mapper.map(note, NotesDto.class))
                .toList();
    }

    @Override
    public Boolean copyNotes(Integer id) throws Exception {

        Notes originalNote = notesRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notes not found"));

        Notes copiedNote = Notes.builder()
                .title(originalNote.getTitle())
                .description(originalNote.getDescription())
                .category(originalNote.getCategory())
                //.isDeleted(false)
                .build();

        Notes savedNote = notesRepo.save(copiedNote);

        return !ObjectUtils.isEmpty(savedNote);
    }
}

