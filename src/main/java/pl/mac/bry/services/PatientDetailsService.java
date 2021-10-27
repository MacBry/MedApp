package pl.mac.bry.services;

import pl.mac.bry.entities.PatientDetails;

public interface PatientDetailsService {

	PatientDetails findPatientDetailsById(long id);
	
	Iterable<PatientDetails> findPatientDetailByPhoneNumber(String phoneNumber);
	
	Iterable<PatientDetails> findPatientDetailByEmail(String email);
	
	Iterable<PatientDetails> getAllPatientDetails();
	
	void addPatientDetails (PatientDetails patientDetails);
	
	void updatePatientDetails (PatientDetails patientDetails);
	
	void deletePAtientDetails (long id);

}
