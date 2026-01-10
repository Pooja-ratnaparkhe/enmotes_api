package com.becoder.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.becoder.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer>{

	//Page<Notes> findByCreatedBy(Integer userId, Pageable pageable);

	Page<Notes> findByCreatedBy(Integer userId, PageRequest pageable);
}
