package com.raj.in.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
//@Table(name = "ELIGIBILITY_DETAILS")
public class EligibilityDetails {
	@Id
//	@Column(name = "ELIGIBILITY_ID")
	private Integer EligibilityId;
	
//	@Column(name = "NAME")
	private String Name;
	
//	@Column(name = "NUMBER")
	private Long number;
	
//	@Column(name = "EMAIL")
	private String email;
	
//	@Column(name = "GENDER")
	private Character gender;
	
//	@Column(name = "SSN")
	private Long ssn;
	
//	@Column(name = "PLAN_NAME")
	private String planName;
	
//	@Column(name = "PLAN_ID")
	private Integer planId;
	
//	@Column(name = "PLAN_STATUS")
	private String planStatus;
	
//	@Column(name = "PLAN_START_DATE")
	private LocalDate planStartDate;
	
//	@Column(name = "PLAN_END_DATE")
	private LocalDate planEndDate;
	
//	@Column(name = "CREATED_BY")
	private String createdBy;
	
//	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
//	@Column(name = "CREATED_DATE")
	private LocalDate createdDate;
	
//	@Column(name = "UPDATED_DATE")
	private LocalDate updatedDate;
}
