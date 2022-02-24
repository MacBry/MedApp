package pl.mac.bry.controllers.mvc;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.DocumentException;

import pl.mac.bry.entities.Sample;
import pl.mac.bry.services.SampleService;
import pl.mac.bry.util.SampleLabelPdfExporter;
import pl.mac.bry.util.StringToDateTimeConverter;

@Controller
public class SampleController {
	
	private SampleService sampleService;
	private long patientId;
	private SampleLabelPdfExporter sampleLabelPdfExporter;

	@Autowired
	public SampleController(SampleService sampleService,SampleLabelPdfExporter sampleLabelPdfExporter) {
		super();
		this.sampleService = sampleService;
		this.sampleLabelPdfExporter =  sampleLabelPdfExporter;
	}
	
	@GetMapping("/patient-samples/{id}")
	public String showPatientSamples(@PathVariable("id") long id, Model model) {
		model.addAttribute("samples", sampleService.findPatientAllSamples(id));
		this.patientId = id; 
		return "show-patient-samples";
	}
	
	@GetMapping("/find-sample")
	public String findSampleById(Model model, @RequestParam("sampleId") long id) {
		try {
			if(sampleService.findSampleById(id).getPatient()==null) {
				return "show-patient-samples";
			}else if (sampleService.findSampleById(id).getPatient().getId() == this.patientId) {
			model.addAttribute("samples", sampleService.findSampleById(id));
			return "show-patient-samples";
			}
			return "show-patient-samples";
		} catch (IllegalArgumentException ex) {
			return "show-patient-samples";
		}
	}
	
	@GetMapping("/show-add-sample-form")
	public String showAddSampleForm(Sample sample) {
		return "add-sample-form";
	}
	
	@PostMapping("/add-sample")
	public String addSample(@Valid Sample sample, BindingResult result, Model model,
			@RequestParam(defaultValue = "") String date) {
		
		if(result.hasErrors()) {
			return "add-sample-form";
		}
		sample.setDonationDateTime(StringToDateTimeConverter.convert(date));
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		sample.setRejestrationDateTime(LocalDateTime.now());
		model.addAttribute(sample);
		sampleService.addSampleToPatient(patientId, sample);
		model.addAttribute("samples", sampleService.findPatientAllSamples(patientId));
		return "show-patient-samples";
	}
	
	@GetMapping("/show-update-sample-form/{id}")
	public String showUpadateForm(@PathVariable("id") long id, Model model) {
		Sample sample = sampleService.findSampleById(id);
		model.addAttribute("sample", sample);
		return "update-sample";
	}
	
	@PostMapping("/update-sample/{id}")
	public String updatePatientSample(@PathVariable("id")long id, @Valid Sample sample, BindingResult result,
			@RequestParam(defaultValue = "") String date) {
		if(result.hasErrors()) {
			sample.setId(id);
			return "update-sample";
		}
		sample.setDonationDateTime(StringToDateTimeConverter.convert(date));
		sampleService.updatePatientSample(id, sample);
		return "redirect:/patient-samples/{id}";
	}
	
	@GetMapping("/print-sample-label/{id}")
	public void exportLabelToPdf(HttpServletResponse response, @PathVariable("id") long id) throws DocumentException, IOException {
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=sample_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        
        Sample sample = sampleService.findSampleById(id);
        sampleLabelPdfExporter.setSample(sample);
        System.out.println(sample.getRejestrationDateTime());
        sampleLabelPdfExporter.export(response);
	}
	
	@GetMapping("/delete-sample/{id}")
	public String deleteSample (@PathVariable("id") long id) {
		sampleService.deleteSample(id);
		
		return "show-patient-samples";
	}
}
