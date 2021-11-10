package pl.mac.bry.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.mac.bry.services.PatientService;

@Controller
public class PatientController {
	
	private PatientService patientService;

	@Autowired
	public PatientController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}
	
	@GetMapping("/show-patients")
	public String showAllPatients(Model model) {
		model.addAttribute("patients", patientService.getAllPatients());
		return "patients";
	}
}
