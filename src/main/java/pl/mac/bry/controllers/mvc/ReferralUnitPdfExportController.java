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
import pl.mac.bry.services.ReferralUnitService;
import pl.mac.bry.services.export.PdfExporter;

@Controller
public class ReferralUnitPdfExportController {

	private ReferralUnitService referralUnitService;
	private PdfExporter<List<ReferralUnit>> multiExporter;
	private PdfExporter<ReferralUnit> exporter;

	@Autowired
	public ReferralUnitPdfExportController(ReferralUnitService referralUnitService,
			@Qualifier("REFF-ADDRESS-MULTI-EXPORT")PdfExporter<List<ReferralUnit>> multiExporter,
			@Qualifier("REF-ADDRESS-LABEL")PdfExporter<ReferralUnit> exporter) {
		super();
		this.referralUnitService = referralUnitService;
		this.multiExporter = multiExporter;
		this.exporter = exporter;
		
	}

	@GetMapping("/print-ref-label/{id}")
	public void exportLabelToPdf(HttpServletResponse response, @PathVariable("id") long id) throws DocumentException, IOException {
		documentFormat(response);
        
        ReferralUnit referralUnit = referralUnitService.findReferralUnitById(id);
        exporter.setModel(referralUnit);
        exporter.export(response);
	}

	

	@GetMapping("/print-ref-label/all")
	public void exportAllLabelToPdf(HttpServletResponse response) throws DocumentException, IOException {
		documentFormat(response);
        
        List<ReferralUnit> referralUnit = (List<ReferralUnit>) referralUnitService.getAllReferralUnits();
        multiExporter.setModel(referralUnit);
        multiExporter.export(response);
	}
	
	private void documentFormat(HttpServletResponse response) {
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=sample_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
	}
}
