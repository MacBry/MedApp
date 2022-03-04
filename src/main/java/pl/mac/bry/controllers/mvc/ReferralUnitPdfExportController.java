package pl.mac.bry.controllers.mvc;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itextpdf.text.DocumentException;

import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.entities.enums.Deadline;
import pl.mac.bry.services.ReferralUnitService;
import pl.mac.bry.services.export.PdfExporter;

@Controller
public class ReferralUnitPdfExportController {

	private ReferralUnitService referralUnitService;
	private PdfExporter<List<ReferralUnit>> multiExporter;
	private PdfExporter<List<ReferralUnit>> a4Exporter;
	private PdfExporter<ReferralUnit> exporter;

	@Autowired
	public ReferralUnitPdfExportController(ReferralUnitService referralUnitService,
			@Qualifier("REFF-ADDRESS-MULTI-EXPORT")PdfExporter<List<ReferralUnit>> multiExporter,
			@Qualifier("REF-ADDRESS-LABEL")PdfExporter<ReferralUnit> exporter,
			@Qualifier("PDF-A4-REF-UNIT-RAPORT-EXPORTER")PdfExporter<List<ReferralUnit>> a4Exporter) {
		super();
		this.referralUnitService = referralUnitService;
		this.multiExporter = multiExporter;
		this.a4Exporter = a4Exporter;
		this.exporter = exporter;
		
	}

	@GetMapping("/print-ref-label/{id}")
	public void exportLabelToPdf(HttpServletResponse response, @PathVariable("id") long id) throws DocumentException, IOException {
		documentFormat(response, "ref_unit_label");
        
        ReferralUnit referralUnit = referralUnitService.findReferralUnitById(id);
        exporter.setModel(referralUnit);
        exporter.export(response);
	}

	

	@GetMapping("/print-ref-label/all")
	public void exportAllLabelToPdf(HttpServletResponse response) throws DocumentException, IOException {
		documentFormat(response, "ref_units_labels");
        
        List<ReferralUnit> referralUnit = (List<ReferralUnit>) referralUnitService.getAllReferralUnits();
        multiExporter.setModel(referralUnit);
        multiExporter.export(response);
	}
	
	@GetMapping("/ref-pdf-list/all")
	public void exportAllReffUnitA4Pdf(HttpServletResponse response) throws DocumentException, IOException {
		documentFormat(response, "ref_unit_full_list");
        
        List<ReferralUnit> referralUnit = (List<ReferralUnit>) referralUnitService.getAllReferralUnits();
        a4Exporter.setModel(referralUnit);
        a4Exporter.export(response);

	}
	
	@GetMapping("/show-choice-referral-unit-by-deadline")
	public String showAddReferralUnitForm(ReferralUnit referralUnit) {
		return "choice-referral-unit-by-deadline";
	}
	
	@GetMapping("/ref-pdf-list/{deadline}")
	public void exportReffUnitA4PdfbyDeadline(HttpServletResponse response, @PathVariable("deadline")Deadline deadline ) throws DocumentException, IOException {
		documentFormat(response, "ref_unit_full_list");
        
        List<ReferralUnit> referralUnit = (List<ReferralUnit>) referralUnitService.findReferralUnitByDeadline(deadline);
        a4Exporter.setModel(referralUnit);
        a4Exporter.export(response);

	}
	
	private void documentFormat(HttpServletResponse response, String name) {
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + name +"_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
	}
}
