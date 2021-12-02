package pl.mac.bry.services;

import java.util.List;
import java.util.function.Function;

import pl.mac.bry.entities.PatientDetails;

public interface PatientDetailsService {

	PatientDetails findPatientDetailsById(long id);
	
	Iterable<PatientDetails> findPatientDetailByPhoneNumber(String phoneNumber);
	
	Iterable<PatientDetails> findPatientDetailByEmail(String email);
	
	Iterable<PatientDetails> getAllPatientDetails();
	
	void addPatientDetails (PatientDetails patientDetails);
	
	void updatePatientDetails (PatientDetails patientDetails);
	
	void deletePAtientDetails (long id);
	
	<T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor);

	PatientDetails findPatientDetails(long patientId);

	void addDetailToPatient(long patientId, PatientDetails patientDetails);

}
