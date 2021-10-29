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

import pl.mac.bry.entities.Patient;
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
	
	@GetMapping("/firstName/{firstName}")
	public Iterable<Patient> getPatientByFirstName(@PathVariable String firstName) {
		return patientService.findPatientByFirstName(firstName);
	}
	
	@GetMapping("/lastName/{lastName}")
	public Iterable<Patient> getPatientByLastName(@PathVariable String lastName) {
		return patientService.findPatientByLastName(lastName);
	}
	
	@GetMapping("/firstName/{firstName}/lastName/{lastName}")
	public Iterable<Patient> getPatientByFirstNameAndLastName(@PathVariable String firstName,
			@PathVariable String lastName){
		return patientService.findPatientByFirstNameAndLastName(firstName, lastName);
	}
	
	@GetMapping("/id/{id}")
	public Patient getPatientById(@PathVariable long id) {
		return patientService.findPatientById(id);
	}
	
	@GetMapping("/pesel/{pesel}")
	public Patient GetPatientByPesel(@PathVariable String pesel) {
		return patientService.findPatientByPesel(pesel);
	}
	
	@GetMapping("/id{id}/details")
	public PatientDetails GetAllPatientDetails(@PathVariable long id) {
		return patientService.findPatientById(id).getPatientDetails();
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
