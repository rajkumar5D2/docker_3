package com.raj.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.raj.in.entity.EligibilityDetails;

@Repository
public interface EligibilityDetailsRepository extends JpaRepository<EligibilityDetails, Integer>{
	
	@Query("select distinct(planName) from EligibilityDetails")
	public List<String> findPlanName();
	
	@Query("select distinct(planStatus) from EligibilityDetails")
	public List<String> findPlanStatus();
}
