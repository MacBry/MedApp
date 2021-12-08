package pl.mac.bry.services;

import pl.mac.bry.entities.PersonToContact;

public interface PersonToContactService {

	PersonToContact findPersonToContactById(long id);
	
	Iterable<PersonToContact> findPersonToContactByFirstName(String firstName);
	
	Iterable<PersonToContact> findPersonToContactByLastName(String lastName);
	
	Iterable<PersonToContact> findPersonToContactByFirstNameAndLastName(String firstName, String lastName);
	
	Iterable<PersonToContact> findPersonToContactByEmail(String email);
	
	Iterable<PersonToContact> findPersonToContactByPhoneNumber(String phoneNumber);
	
	Iterable<PersonToContact> getAllPersonsToContact();
	
	void addPersonToContact (PersonToContact personToContact);
	
	void updatePersonToContact (PersonToContact personToContact);
	
	void deletePersonToContact (long id);

	Iterable<PersonToContact> findPatientAllPersonsToContact(long patientId);
}
