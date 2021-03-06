package pl.mac.bry.services;

import java.util.List;
import java.util.function.Function;

import pl.mac.bry.entities.Patient;

public interface PatientService {
	
	Patient findPatientById(long id);
	
	Iterable<Patient> findPatientByFirstName(String firstName);
	
	Iterable<Patient> findPatientByLastName(String lastName);
	
	Iterable<Patient> findPatientByFirstNameAndLastName(String firstName, String lastName);
	
	Iterable<Patient> getAllPatients();
	
	Patient findPatientByPesel(String pesel);
	
	void addPatient(Patient patient);
	
	void updatePatient(Patient patient);
	
	void deletePatient(long id);

	<T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor);
}
