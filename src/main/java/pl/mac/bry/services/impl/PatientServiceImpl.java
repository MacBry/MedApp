package pl.mac.bry.services.impl;

import org.audit4j.core.annotation.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mac.bry.entities.Patient;
import pl.mac.bry.repositories.PatientRepository;
import pl.mac.bry.services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {
	
	private PatientRepository patientRepository;
	
	@Autowired
	public PatientServiceImpl(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	@Override
	@Audit(action = "PatientServiceImpl.findPatientById()")
	public Patient findPatientById(long id) {
		return patientRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid patient id: " + id));
	}

	@Override
	@Audit(action = "PatientServiceImpl.findPatientByFirstName()")
	public Iterable<Patient> findPatientByFirstName(String firstName) {
		return patientRepository.findByFirstName(firstName);
	}

	@Override
	@Audit(action = "PatientServiceImpl.findPatientByLastName()")
	public Iterable<Patient> findPatientByLastName(String lastName) {
		return patientRepository.findByLastName(lastName);
	}

	@Override
	@Audit(action = "PatientServiceImpl.getAllPatients()")
	public Iterable<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	@Audit(action = "PatientServiceImpl.addPatient()")
	public void addPatient(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	@Audit(action = "PatientServiceImpl.updatePatient()")
	public void updatePatient(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	@Audit(action = "PatientServiceImpl.deletePatient()")
	public void deletePatient(long id) {
		Patient patient = findPatientById(id);
		patientRepository.delete(patient);
	}

}
