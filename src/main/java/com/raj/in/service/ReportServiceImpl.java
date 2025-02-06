package com.raj.in.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.raj.in.dto.SearchResponse;
import com.raj.in.dto.SearchResquest;
import com.raj.in.entity.EligibilityDetails;
import com.raj.in.repository.EligibilityDetailsRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private EligibilityDetailsRepository eligibilityDetails;

	@Override
	public List<SearchResponse> search(SearchResquest request) {
		// TODO Auto-generated method stub
		EligibilityDetails requestBuilder = new EligibilityDetails();
		List<SearchResponse> response = new ArrayList<>();
		// IF USER APPLIES ANY ONE OF THE FILTER
		if (request.getPlanName() != null && !request.getPlanName().isEmpty()) {
			requestBuilder.setPlanName(request.getPlanName().trim());
		}
		if (request.getPlanStatus() != null && request.getPlanStatus() != "") {
			requestBuilder.setPlanStatus(request.getPlanStatus());
		}
		if (request.getPlanStartDate() != null) {
			requestBuilder.setPlanStartDate(request.getPlanStartDate());
		}
		if (request.getPlanEndDate() != null) {
			requestBuilder.setPlanEndDate(request.getPlanEndDate());
		}
		// USING QBE TO EXECUTE DYNAMIC SEARCH
//		ExampleMatcher matcher = ExampleMatcher.matching()
								// .withIgnoreNullValues()
								// .withIgnorePaths("number", "ssn") // Ignore
								//	.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();
		Example<EligibilityDetails> example = Example.of(requestBuilder);
		System.out.println("Example used for search: " + example);
		List<EligibilityDetails> all = eligibilityDetails.findAll(example);
		all.forEach(n -> {
			SearchResponse r = new SearchResponse();
			BeanUtils.copyProperties(n, r);
			response.add(r);
		});
		return response;
	}

	@Override
	public List<String> getPlanNames() {
		// TODO Auto-generated method stub
		return eligibilityDetails.findPlanName();
	}

	@Override
	public List<String> getPlanStatus() {
		// TODO Auto-generated method stub
		return eligibilityDetails.findPlanStatus();
	}

	@Override
	public void generateExcel(HttpServletResponse httpServletResponse) throws Exception {
		// TODO Auto-generated method stub
		List<EligibilityDetails> all = eligibilityDetails.findAll();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow headerRow = sheet.createRow(0);
		// headerRow.createCell(0).setCellValue("s.no");
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Mobile");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Email");
		headerRow.createCell(4).setCellValue("SSN");
		int[] i = { 1 };
		all.forEach(n -> {
			HSSFRow dataRow = sheet.createRow(i[0]);
			dataRow.createCell(0).setCellValue(n.getName());
			dataRow.createCell(1).setCellValue(n.getNumber());
			dataRow.createCell(2).setCellValue(String.valueOf(n.getGender()));
			dataRow.createCell(3).setCellValue(n.getEmail());
			dataRow.createCell(4).setCellValue(n.getSsn());
			i[0]++;
		});
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	@Override
	public void generatePdf(HttpServletResponse httpServletResponse) throws DocumentException, IOException {
		// TODO Auto-generated method stub
		List<EligibilityDetails> all = eligibilityDetails.findAll();

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, httpServletResponse.getOutputStream());

		document.open();

		Paragraph title = new Paragraph("Search Report");
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);

		PdfPTable table = new PdfPTable(4); // creating table with fixed columns
		table.setWidthPercentage(100);
		table.setSpacingBefore(10f);
		table.setWidths(new float[] { 2.5f, 3.0f, 5.0f, 3.0f });
		table.addCell("name"); // creating headers
		table.addCell("mobile");
		table.addCell("email");
		table.addCell("SSN");

		for (EligibilityDetails e : all) { // setting db data
			table.addCell(e.getName());
			table.addCell(String.valueOf(e.getNumber()));
			table.addCell(e.getEmail());
			table.addCell(String.valueOf(e.getSsn()));
		}
		document.add(table);
		document.close();
	}

}
