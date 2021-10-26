package pl.mac.bry.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long> {

}
