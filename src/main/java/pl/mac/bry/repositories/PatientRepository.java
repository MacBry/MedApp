package pl.mac.bry.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {
	
	Iterable<Patient> findByFirstName(String firstName);
	
	Iterable<Patient> findByLastName(String lastName);
	
	Optional<Patient> findByPesel(String pesel);
}
