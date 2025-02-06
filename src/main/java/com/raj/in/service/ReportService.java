package com.raj.in.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;
import com.raj.in.dto.SearchResponse;
import com.raj.in.dto.SearchResquest;

import jakarta.servlet.http.HttpServletResponse;

@Service
public interface ReportService {
//	public List<EligibilityDetails> eligibleCandidates();

	public List<SearchResponse> search(SearchResquest request);

	public List<String> getPlanNames();

	public List<String> getPlanStatus();

	public void generateExcel(HttpServletResponse httpServletResponse) throws Exception;

	public void generatePdf(HttpServletResponse httpServletResponse) throws DocumentException, IOException;
}
