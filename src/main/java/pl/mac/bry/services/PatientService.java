package pl.mac.bry.services;

import pl.mac.bry.entities.Patient;

public interface PatientService {
	
	Patient findPatientById(long id);
	
	Iterable<Patient> findPatientByFirstName(String firstName);
	
	Iterable<Patient> findPatientByLastName(String lastName);
	
	Iterable<Patient> getAllPatients();
	
	void addPatient(Patient patient);
	
	void updatePatient(Patient patient);
	
	void deletePatient(long id);
}
