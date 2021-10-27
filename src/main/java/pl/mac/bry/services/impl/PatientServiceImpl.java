package pl.mac.bry.services.impl;

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
	public Patient findPatientById(long id) {
		return patientRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid patient id: " + id));
	}

	@Override
	public Iterable<Patient> findPatientByFirstName(String firstName) {
		return patientRepository.findByFirstName(firstName);
	}

	@Override
	public Iterable<Patient> findPatientByLastName(String lastName) {
		return patientRepository.findByLastName(lastName);
	}

	@Override
	public Iterable<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@Override
	public void addPatient(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public void updatePatient(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public void deletePatient(long id) {
		Patient patient = findPatientById(id);
		patientRepository.delete(patient);
	}

}
