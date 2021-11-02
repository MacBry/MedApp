package pl.mac.bry.services.impl;

import org.audit4j.core.annotation.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mac.bry.entities.PersonToContact;
import pl.mac.bry.repositories.PersonToContactRepository;
import pl.mac.bry.services.PersonToContactService;

@Service
public class PersonToContactServiceImpl implements PersonToContactService {
	
	private PersonToContactRepository personToContactRepository;
	
	@Autowired
	public PersonToContactServiceImpl(PersonToContactRepository personToContactRepository) {
		super();
		this.personToContactRepository = personToContactRepository;
	}

	@Override
	@Audit(action = "PersonToContactServiceImpl.findPersonToContactById()")
	public PersonToContact findPersonToContactById(long id) {
		return personToContactRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Person to contact id: " + id));
	}

	@Override
	@Audit(action = "PersonToContactServiceImpl.findPersonToContactByFirstName()")
	public Iterable<PersonToContact> findPersonToContactByFirstName(String firstName) {
		return personToContactRepository.findByFirstName(firstName);
	}

	@Override
	@Audit(action = "PersonToContactServiceImpl.findPersonToContactByLastName()")
	public Iterable<PersonToContact> findPersonToContactByLastName(String lastName) {
		return personToContactRepository.findByLastName(lastName);
	}
	
	@Override
	@Audit(action = "PersonToContactServiceImpl.findPersonToContactByFirstNameAndLastName()")
	public Iterable<PersonToContact> findPersonToContactByFirstNameAndLastName(String firstName, String lastName) {
		return personToContactRepository.findByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	@Audit(action = "PersonToContactServiceImpl.findPersonToContactByEmail()")
	public Iterable<PersonToContact> findPersonToContactByEmail(String email) {
		return personToContactRepository.findByEmail(email);
	}

	@Override
	@Audit(action = "PersonToContactServiceImpl.findPersonToContactByPhoneNumber()")
	public Iterable<PersonToContact> findPersonToContactByPhoneNumber(String phoneNumber) {
		return personToContactRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	@Audit(action = "PersonToContactServiceImpl.getAllPersonsToContact()")
	public Iterable<PersonToContact> getAllPersonsToContact() {
		return personToContactRepository.findAll();
	}

	@Override
	@Audit(action = "PersonToContactServiceImpl.addPersonToContact()")
	public void addPersonToContact(PersonToContact personToContact) {
		personToContactRepository.save(personToContact);
	}

	@Override
	@Audit(action = "PersonToContactServiceImpl.updatePersonToContact()")
	public void updatePersonToContact(PersonToContact personToContact) {
		personToContactRepository.save(personToContact);
	}

	@Override
	@Audit(action = "PersonToContactServiceImpl.deletePersonToContact()")
	public void deletePersonToContact(long id) {
		PersonToContact personToContact =  findPersonToContactById(id);
		personToContactRepository.delete(personToContact);
	}


}
