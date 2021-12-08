package pl.mac.bry.services.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.audit4j.core.annotation.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mac.bry.entities.Patient;
import pl.mac.bry.entities.PatientDetails;
import pl.mac.bry.repositories.PatientDetailsRepository;
import pl.mac.bry.services.PatientDetailsService;
import pl.mac.bry.services.PatientService;

@Service
public class PatientDetailsServiceImpl implements PatientDetailsService {
	
	private PatientDetailsRepository patientDetailsRepository;
	private PatientService patientService;
	
	@Autowired
	public PatientDetailsServiceImpl(PatientDetailsRepository patientDetailsRepository,
			PatientService patientService) {
		super();
		this.patientDetailsRepository = patientDetailsRepository;
		this.patientService = patientService;
	}

	@Override
	@Audit(action = "PatientDetailsServiceImpl.findPatientDetailsById()")
	public PatientDetails findPatientDetailsById(long id) {
		
		return patientDetailsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Patient Detail id " + id));
	}

	@Override
	@Audit(action = "PatientDetailsServiceImpl.findPatientDetailByPhoneNumber()")
	public Iterable<PatientDetails> findPatientDetailByPhoneNumber(String phoneNumber) {		
		return patientDetailsRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	@Audit(action = "PatientDetailsServiceImpl.findPatientDetailByEmail()")
	public Iterable<PatientDetails> findPatientDetailByEmail(String email) {
		return patientDetailsRepository.findByEmail(email);
	}

	@Override
	@Audit(action = "PatientDetailsServiceImpl.getAllPatientDetails()")
	public Iterable<PatientDetails> getAllPatientDetails() {
		return patientDetailsRepository.findAll();
	}

	@Override
	@Audit(action = "PatientDetailsServiceImpl.addPatientDetails()")
	public void addPatientDetails(PatientDetails patientDetails) {
		patientDetailsRepository.save(patientDetails);
	}

	@Override
	@Audit(action = "PatientDetailsServiceImpl.updatePatientDetails()")
	public void updatePatientDetails(PatientDetails patientDetails) {
		patientDetailsRepository.save(patientDetails);
	}

	@Override
	@Audit(action = "PatientDetailsServiceImpl.deletePAtientDetails()")
	public void deletePAtientDetails(long id) {
		Patient patient = patientService.findPatientById(id);
		long detailId =patient.getPatientDetails().getId();
		patient.setPatientDetails(null);
		PatientDetails patientDetails = findPatientDetailsById(detailId);
		patientDetailsRepository.delete(patientDetails);
	}

	@Override
	@Audit(action = "PatientDetailsServiceImpl.getValues()")
	public <T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor) {
		return listToExtractFrom.stream().map(extractor).collect(Collectors.toList());
	}

	@Override
	@Audit(action = "PatientDetailsServiceImpl.findPatientDetails()")
	public PatientDetails findPatientDetails(long patientId) {
		Patient patient = patientService.findPatientById(patientId);
		return patient.getPatientDetails();
	}
	
	@Override
	@Audit(action = "PatientDetailsServiceImpl.addDetailToPatient()")
	public void addDetailToPatient(long patientId, PatientDetails patientDetails) {
		Patient patient = patientService.findPatientById(patientId);
		patientDetailsRepository.save(patientDetails);
		patient.addPatientDetail(patientDetails);
		patientService.updatePatient(patient);
	}

}
