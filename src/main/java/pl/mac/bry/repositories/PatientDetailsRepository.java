package pl.mac.bry.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.PatientDetails;

public interface PatientDetailsRepository extends CrudRepository<PatientDetails, Long> {
	
	PatientDetails findPatientDetailsById(long id);
	
	Iterable<PatientDetails> findByPhoneNumber(String phoneNumber);
	
	Iterable<PatientDetails> findByEmail(String email);
	
}
