package com.raj.in.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.raj.in.dto.SearchResponse;
import com.raj.in.dto.SearchResquest;
import com.raj.in.service.ReportService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportRestController {
	@Autowired
	private ReportService service;

	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlansNames() {
		List<String> planNames = service.getPlanNames();
		return new ResponseEntity<List<String>>(planNames, HttpStatus.OK);
	}

	@GetMapping("/planStatus")
	public ResponseEntity<List<String>> getPlanStatus() {
		List<String> planStatus = service.getPlanStatus();
		return new ResponseEntity<List<String>>(planStatus, HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchResquest request) {
		List<SearchResponse> search = service.search(request);
		return new ResponseEntity<List<SearchResponse>>(search, HttpStatus.OK);
	}

	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.xls";
		response.setHeader(headerKey, headerValue);
		service.generateExcel(response);
	}

	@GetMapping("/pdf")
	public void excelPdf(HttpServletResponse response) {
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=data.pdf";
		response.setHeader(headerKey, headerValue);
		try {
			service.generatePdf(response);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
