package pl.mac.bry.controllers.mvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.mac.bry.entities.Sample;
import pl.mac.bry.services.SampleService;
import pl.mac.bry.util.StringToDateTimeConverter;

@Controller
public class SampleController {
	
	private SampleService sampleService;
	private long id;

	@Autowired
	public SampleController(SampleService sampleService) {
		super();
		this.sampleService = sampleService;
	}
	
	@GetMapping("/patient-samples/{id}")
	public String showPatientSamples(@PathVariable("id") long id, Model model) {
		model.addAttribute("samples", sampleService.findPatientAllSamples(id));
		this.id = id;
		return "show-patient-samples";
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
		model.addAttribute(sample);
		sampleService.addSampleToPatient(id, sample);
		model.addAttribute("samples", sampleService.findPatientAllSamples(id));
		return "show-patient-samples";
	}
}
