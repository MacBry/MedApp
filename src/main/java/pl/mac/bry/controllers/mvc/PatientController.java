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

import pl.mac.bry.entities.Patient;
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
	
	@GetMapping("/find-patients")
	public String findPatientByPESEL(Model model, @RequestParam("pesel") String pesel) {
		
		try {
			model.addAttribute("patients", patientService.findPatientByPesel(pesel));
			return "patients";
			
		} catch (IllegalArgumentException ex) {
			model.addAttribute("patients", patientService.getAllPatients());
			return "patients";
		}
	}
	
	@GetMapping("/show-add-patient-form")
	public String showAddPatientForm(Patient patient) {
		return "add-patient-form";
	}
	
	@PostMapping("/add-patient")
	public String addPatient(@Valid Patient patient, BindingResult result, Model model) {
		model.addAttribute(patient);
		if(result.hasErrors()) {
			return "add-patient";
		}
		patientService.addPatient(patient);
		return "redirect:/show-patients";
	}
	
	@GetMapping("/show-update-patient-form/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Patient patient =patientService.findPatientById(id);
		model.addAttribute("patient", patient );
		return "update-patient-form";
	}
	
	@PostMapping("/update-patient/{id}")
	public String upadatePatient(@PathVariable("id") long id, @Valid Patient patient, BindingResult result) {
		if (result.hasErrors()) {
            return "update-user";
        }
        
        patientService.updatePatient(patient);

        return "redirect:/patients";
	}
	
	@GetMapping("/delete-patient/{id}")
	public String deletePatient(@PathVariable("id") long id) {
		patientService.deletePatient(id);
		return "redirect:/show-patients";
	}
}
