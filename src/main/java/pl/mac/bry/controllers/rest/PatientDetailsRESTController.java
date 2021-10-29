package pl.mac.bry.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.mac.bry.entities.PatientDetails;
import pl.mac.bry.services.PatientDetailsService;


@RestController
@RequestMapping("/api/patientDetails")
public class PatientDetailsRESTController {
	
	private PatientDetailsService patientDetailsService;
	
	@Autowired
	public PatientDetailsRESTController(PatientDetailsService patientDetailsService) {
		super();
		this.patientDetailsService = patientDetailsService;
	}

	@GetMapping("/")
	public Iterable<PatientDetails> getAllPatientDetails() {
		return patientDetailsService.getAllPatientDetails();
	}
	
	@GetMapping("/phoneNumber/{phoneNumber}")
	public Iterable<PatientDetails> getPatientDetailsByPhoneNumber(@PathVariable String phoneNumber){
		return patientDetailsService.findPatientDetailByPhoneNumber(phoneNumber);
	}
	
	@GetMapping("/email/{email}")
	public Iterable<PatientDetails> getPaientDetailsByEmail(@PathVariable String email){
		return patientDetailsService.findPatientDetailByEmail(email);
	}
	
	@GetMapping("/id/{id}")
	public PatientDetails getPatientById(@PathVariable long id) {
		return patientDetailsService.findPatientDetailsById(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void savePaientDetails(@RequestBody PatientDetails patientDetails) {
		patientDetailsService.addPatientDetails(patientDetails);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePatientDetails(@RequestBody PatientDetails patientDetails) {
		patientDetailsService.updatePatientDetails(patientDetails);
	}
	
	@DeleteMapping("/delete/id/{id}")
	public void deletePatientDetails(@PathVariable long id) {
		patientDetailsService.deletePAtientDetails(id);
	}
}
