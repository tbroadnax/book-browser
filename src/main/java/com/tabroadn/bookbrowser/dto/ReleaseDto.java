package com.tabroadn.bookbrowser.dto;

import java.time.LocalDate;

import com.tabroadn.bookbrowser.domain.ReleaseTypeEnum;

import lombok.Data;

@Data
public class ReleaseDto {
	private Long id;
	
	private String description;
	
	private ReleaseTypeEnum releaseType;
	
	private Integer releaseNumber;
	
	private LocalDate publishDate;
}
