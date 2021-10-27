package pl.mac.bry.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.PersonToContact;

public interface PersonToContactRepository extends CrudRepository<PersonToContact, Long> {
	
	Iterable<PersonToContact> findByFirstName(String firstName);
	
	Iterable<PersonToContact> findByLastName(String lastName);
	
	Iterable<PersonToContact> findByEmail(String email);
	
	Iterable<PersonToContact> findByPhoneNumber(String phoneNumber);
	
	Iterable<PersonToContact> getAllPersonsToContact();
	
}
