package pl.mac.bry.controllers.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.mac.bry.entities.Sample;
import pl.mac.bry.services.SampleService;

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
		System.out.println(id);
		this.id = id;
		return "show-patient-samples";
	}
	
	@GetMapping("/show-add-sample-form")
	public String showAddSampleForm(Sample sample) {
		return "add-sample-form";
	}
	
	@PostMapping("/add-sample")
	public String addSample(@Valid Sample sample, BindingResult result, Model model) {
		model.addAttribute(sample);
		if(result.hasErrors()) {
			return "add-sample-form";
		}
		sampleService.addSampleToPatient(id, sample);
		model.addAttribute("samples", sampleService.findPatientAllSamples(id));
		return "show-patient-samples";
	}
}
