package pl.mac.bry.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mac.bry.entities.PatientDetails;
import pl.mac.bry.repositories.PatientDetailsRepository;
import pl.mac.bry.services.PatientDetailsService;

@Service
public class PatientDetailsServiceImpl implements PatientDetailsService {
	
	private PatientDetailsRepository patientDetailsRepository;
	
	@Autowired
	public PatientDetailsServiceImpl(PatientDetailsRepository patientDetailsRepository) {
		super();
		this.patientDetailsRepository = patientDetailsRepository;
	}

	@Override
	public PatientDetails findPatientDetailsById(long id) {
		
		return patientDetailsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Patient Detail id " + id));
	}

	@Override
	public Iterable<PatientDetails> findPatientDetailByPhoneNumber(String phoneNumber) {		
		return patientDetailsRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public Iterable<PatientDetails> findPatientDetailByEmail(String email) {
		return patientDetailsRepository.findByEmail(email);
	}

	@Override
	public Iterable<PatientDetails> getAllPatientDetails() {
		return patientDetailsRepository.findAll();
	}

	@Override
	public void addPatientDetails(PatientDetails patientDetails) {
		patientDetailsRepository.save(patientDetails);
	}

	@Override
	public void updatePatientDetails(PatientDetails patientDetails) {
		patientDetailsRepository.save(patientDetails);
	}

	@Override
	public void deletePAtientDetails(long id) {
		PatientDetails patientDetails = findPatientDetailsById(id);
		patientDetailsRepository.delete(patientDetails);
	}

	

}
