package pl.mac.bry.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
		this.id = id;
		return "show-patient-samples";
	}
}
