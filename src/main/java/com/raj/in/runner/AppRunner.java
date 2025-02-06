package com.raj.in.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.raj.in.entity.EligibilityDetails;
import com.raj.in.repository.EligibilityDetailsRepository;

@Component
public class AppRunner implements ApplicationRunner{

	@Autowired
	EligibilityDetailsRepository repo;
	
	public AppRunner() {
		System.out.println("apprunner executed%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		EligibilityDetails e1 = new EligibilityDetails();
		e1.setEligibilityId(1);
		e1.setName("raj");
		e1.setCreatedBy("raj");
		e1.setEmail("rajkumarmikkili@gmail.com");
		e1.setGender('M');
		e1.setNumber(8897300468L);
		e1.setSsn(12345678L);
		e1.setPlanName("paypal");
		e1.setPlanStatus("approved");
		repo.save(e1);
		
		EligibilityDetails e2 = new EligibilityDetails();
		e2.setEligibilityId(2);
		e2.setName("rani");
		e2.setCreatedBy("rani");
		e2.setEmail("rajkumarmikkili123@gmail.com");
		e2.setGender('F');
		e2.setNumber(8897300469L);
		e2.setSsn(123412378L);
		e2.setPlanName("paytm");
		e2.setPlanStatus("denied");
		repo.save(e2);
		
	}

}
