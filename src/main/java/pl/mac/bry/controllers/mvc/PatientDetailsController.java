package pl.mac.bry.controllers.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.mac.bry.entities.PatientDetails;
import pl.mac.bry.services.PatientDetailsService;

@Controller
public class PatientDetailsController {

	private PatientDetailsService patientDetailsService;
	private long id;

	@Autowired
	public PatientDetailsController(PatientDetailsService patientDetailsService) {
		super();
		this.patientDetailsService = patientDetailsService;
	}
	
	@GetMapping("/patient-details/{id}")
	public String showPatientDetailString(@PathVariable("id") long id, Model model) {
		model.addAttribute("details", patientDetailsService.findPatientDetails(id));
		this.id = id;
		return "show-patient-details";
	}
	
	@GetMapping("show-add-patient-detail-form")
	public String showAddDetailForm(PatientDetails patientDetails) {
		return "add-patient-detail-form";
	}
	
	@PostMapping("/add-detail")
	public String addDetail(@Valid PatientDetails patientDetails, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add-patient-detail-form";
		}
		model.addAttribute(patientDetails);
		patientDetailsService.addDetailToPatient(id, patientDetails);
		model.addAttribute("details", patientDetailsService.findPatientDetails(id));
		return "show-patient-details";
	}
			
}
