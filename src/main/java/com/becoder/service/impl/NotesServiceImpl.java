package com.becoder.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.becoder.dto.NotesDto;
import com.becoder.dto.NotesDto.CategoryDto;
import com.becoder.entity.Category;
import com.becoder.entity.Notes;
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

	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public Boolean saveNotes(NotesDto notesDto) throws Exception {

		// category validation
		checkCategoryExist(notesDto.getCategory());

		Notes notes = mapper.map(notesDto, Notes.class);
		Notes saveNotes = notesRepo.save(notes);
		if (!ObjectUtils.isEmpty(saveNotes)) {
			return true;
		}
		return false;
	}

	private void checkCategoryExist(CategoryDto category) throws Exception {
		categoryRepo.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("category id invalid"));
	}

	@Override
	public List<NotesDto> getAllNotes() {
		return notesRepo.findAll().stream().map(note -> mapper.map(note, NotesDto.class)).toList();
	}

<<<<<<< Updated upstream
}
=======
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

	@Override
	public Boolean copyNotes(Integer id) throws Exception {
		
		 // 1️⃣ Find original note
        Notes originalNote = notesRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Notes not found"));

        // 2️⃣ Create new copied note
        Notes copiedNote = Notes.builder()
            .title(originalNote.getTitle())
            .description(originalNote.getDescription())
            .category(originalNote.getCategory())
            .isDeleted(false)
            .build();

        // 3️⃣ Save copied note
        Notes savedNote = notesRepo.save(copiedNote);

        // 4️⃣ Return result
        return !ObjectUtils.isEmpty(savedNote);
    }
}
>>>>>>> Stashed changes
