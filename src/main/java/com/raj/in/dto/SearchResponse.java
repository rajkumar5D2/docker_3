package com.raj.in.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchResponse {

	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
}
