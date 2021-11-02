package pl.mac.bry.controllers.rest;

import java.util.ArrayList;
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

import pl.mac.bry.entities.Patient;
import pl.mac.bry.entities.Sample;
import pl.mac.bry.entities.PatientDetails;
import pl.mac.bry.services.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientRESTController {
	
	private PatientService patientService;

	@Autowired
	public PatientRESTController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}
	
	@GetMapping("/")
	public Iterable<Patient> getAllPatients() {
		return patientService.getAllPatients();
	}
	
	@GetMapping("/id/{id}")
	public Patient getPatientById(@PathVariable long id) {
		return patientService.findPatientById(id);
	}
	
	@GetMapping("/id/{id}/first-name")
	public String getPatientFirstName(@PathVariable long id) {
		return patientService.findPatientById(id).getFirstName();
	}
	
	@GetMapping("/first-names")
	public List<String> getAllPatientsFirstNames() {
		List<Patient> allPatients = (List<Patient>)patientService.getAllPatients();
		return patientService.getValues(String.class, allPatients, Patient::getFirstName);
	}
	
	@GetMapping("/id/{id}/last-name")
	public String getPatientLastName(@PathVariable long id) {
		return patientService.findPatientById(id).getLastName();
	}
	
	@GetMapping("/last-names")
	public List<String> getAllPatientsLastNames() {
		List<Patient> allPatients = (List<Patient>)patientService.getAllPatients();
		return patientService.getValues(String.class,allPatients, Patient::getLastName);
	}
	
	@GetMapping("/id/{id}/pesel")
	public String getPatientPessel(@PathVariable long id) {
		return patientService.findPatientById(id).getPesel();
	}
	
	@GetMapping("/pesels")
	public List<String> getAllPatientPesels() {
		List<Patient> allPatients = (List<Patient>)patientService.getAllPatients();
		return patientService.getValues(String.class, allPatients, Patient::getPesel);
	}
	
	@GetMapping("/id/{id}/patient-detail")
	public PatientDetails getPatientDetail(@PathVariable long id) {
		return patientService.findPatientById(id).getPatientDetails();
	}
	
	@GetMapping("/patients-details")
	public List<PatientDetails> getAllPatientsDetails() {
		List<Patient> allPatients = (List<Patient>)patientService.getAllPatients();
		return patientService.getValues(PatientDetails.class, allPatients, Patient::getPatientDetails);
	}
	
	@GetMapping("/id/{id}/samples")
	public List<Sample> getPatientSamples(@PathVariable long id) {
		return patientService.findPatientById(id).getPatientSamples();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void savePatient(@RequestBody Patient patient) {
		patientService.addPatient(patient);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePatient(@RequestBody Patient patient) {
		patientService.updatePatient(patient);
	}
	
	@DeleteMapping("/delete/id/{id}")
	public void deletePatient(@PathVariable long id) {
		patientService.deletePatient(id);
	}
}
