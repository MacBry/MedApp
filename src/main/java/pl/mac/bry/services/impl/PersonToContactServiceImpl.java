package pl.mac.bry.services.impl;

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
	public PersonToContact findPersonToContactById(long id) {
		return personToContactRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Person to contact id: " + id));
	}

	@Override
	public Iterable<PersonToContact> findPersonToContactByFirstName(String firstName) {
		return personToContactRepository.findByFirstName(firstName);
	}

	@Override
	public Iterable<PersonToContact> findPersonToContactByLastName(String lastName) {
		return personToContactRepository.findByLastName(lastName);
	}

	@Override
	public Iterable<PersonToContact> findPersonToContactByEmail(String email) {
		return personToContactRepository.findByEmail(email);
	}

	@Override
	public Iterable<PersonToContact> findPersonToContactByPhoneNumber(String phoneNumber) {
		return personToContactRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public Iterable<PersonToContact> getAllPersonsToContact() {
		return personToContactRepository.findAll();
	}

	@Override
	public void addPersonToContact(PersonToContact personToContact) {
		personToContactRepository.save(personToContact);
	}

	@Override
	public void updatePersonToContact(PersonToContact personToContact) {
		personToContactRepository.save(personToContact);
	}

	@Override
	public void deletePersonToContact(long id) {
		PersonToContact personToContact =  findPersonToContactById(id);
		personToContactRepository.delete(personToContact);
	}

}
