package pl.mac.bry.controllers.rest;

import java.util.List;

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

import pl.mac.bry.entities.Address;
import pl.mac.bry.entities.PatientDetails;
import pl.mac.bry.entities.PersonToContact;
import pl.mac.bry.services.PatientDetailsService;


@RestController
@RequestMapping("/api/patient-details")
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
	
	@GetMapping("/id/{id}")
	public PatientDetails getPatientDetailsById(@PathVariable long id) {
		return patientDetailsService.findPatientDetailsById(id);
	}
	
	@GetMapping("/id/{id}/persons-to-contact")
	public List<PersonToContact> getPatientDetailsPersonsToContact (@PathVariable long id){
		return patientDetailsService.findPatientDetailsById(id).getPersonsToContact();
	}
	
	@GetMapping("/id/{id}/adresses")
	public List<Address> getPatientDetailsAdresses(@PathVariable long id){
		return patientDetailsService.findPatientDetailsById(id).getPatientAdresses();
	}
	
	@GetMapping("/id/{id}/phone-number")
	public String getPatientDetailsPhoneNumber(@PathVariable long id) {
		return patientDetailsService.findPatientDetailsById(id).getPhoneNumber();
	}
	
	@GetMapping("phones-numbers")
	public List<String> getAllPatientsPhonesNumbers() {
		List<PatientDetails> allDetails = (List<PatientDetails>) patientDetailsService.getAllPatientDetails();
		return patientDetailsService.getValues(String.class, allDetails, PatientDetails::getPhoneNumber);
	}
	
	@GetMapping("/id/{id}/email")
	public String getPatientDetailsEmail (@PathVariable long id) {
		return patientDetailsService.findPatientDetailsById(id).getEmail();
	}
	
	public List<String> getAllPatientDetailsEmails () {
		List<PatientDetails> allDetails = (List<PatientDetails>) patientDetailsService.getAllPatientDetails();
		return patientDetailsService.getValues(String.class, allDetails, PatientDetails::getEmail);
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
