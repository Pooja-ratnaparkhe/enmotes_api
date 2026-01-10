package com.becoder.entity;

import java.util.List;

import com.becoder.dto.NotesDto;

import lombok.Builder;

import lombok.Data;



@Data
@Builder
public class NotesResponse {

	private List<NotesDto> notes;

	private Integer pageNo;
	
	private Integer pageSize;
	
	private Long totalElements;
	
	private Integer totalPages;
	
	private Boolean isFirst;
	
	private Boolean isLast;

}


