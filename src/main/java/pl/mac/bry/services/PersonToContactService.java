package pl.mac.bry.services;

import pl.mac.bry.entities.PersonToContact;

public interface PersonToContactService {

	PersonToContact findById(long id);
	
	Iterable<PersonToContact> findPersonToContactByFirstName(String firstName);
	
	Iterable<PersonToContact> findPersonToContactByLastName(String lastName);
	
	Iterable<PersonToContact> findPersonToContactByEmail(String email);
	
	Iterable<PersonToContact> findPersonToContactByPhoneNumber(String phoneNumber);
	
	Iterable<PersonToContact> getAllPersonsToContact();
	
	void addPersonToContact (PersonToContact personToContact);
	
	void updatePersonToContact (PersonToContact personToContact);
	
	void deletePersonToContact (long id);
}
