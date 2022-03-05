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

import pl.mac.bry.entities.Sample;
import pl.mac.bry.services.SampleService;
import pl.mac.bry.services.export.PdfExporter;
import pl.mac.bry.util.SampleLabelPdfExporter;

@Controller
public class SamplePdfExporterController {
	
	private SampleService sampleService;
	private SampleLabelPdfExporter sampleLabelPdfExporter;
	private PdfExporter<List<Sample>> sampleRaportPdfExporter;
	
	
	@Autowired
	public SamplePdfExporterController(SampleService sampleService, 
			SampleLabelPdfExporter sampleLabelPdfExporter,
			@Qualifier("PDF-A4-SAMPLE-RAPORT-EXPORTER")PdfExporter<List<Sample>> sampleRaportPdfExporter) {
		super();
		this.sampleService = sampleService;
		this.sampleLabelPdfExporter = sampleLabelPdfExporter;
		this.sampleRaportPdfExporter = sampleRaportPdfExporter;
	}

	@GetMapping("/print-sample-label/{id}")
	public void exportLabelToPdf(HttpServletResponse response, @PathVariable("id") long id) throws DocumentException, IOException {
		filePreparation(response, "label");
        
        Sample sample = sampleService.findSampleById(id);
        sampleLabelPdfExporter.setSample(sample);
        sampleLabelPdfExporter.export(response);
	}

	
	@GetMapping("/print-sample-raport")
	public void exportRaport(HttpServletResponse response) throws DocumentException, IOException {
		filePreparation(response, "A4raport");
        
       List<Sample> samples =(List<Sample>) sampleService.getAllSamples();
       sampleRaportPdfExporter.setModel(samples);
       sampleRaportPdfExporter.export(response);
	}
	
	private void filePreparation(HttpServletResponse response , String raportName) {
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=sample_"+raportName+"_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
	}
}
