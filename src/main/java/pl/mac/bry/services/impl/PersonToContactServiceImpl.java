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
	public PersonToContact findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<PersonToContact> findPersonToContactByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<PersonToContact> findPersonToContactByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<PersonToContact> findPersonToContactByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<PersonToContact> findPersonToContactByPhoneNumber(String phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<PersonToContact> getAllPersonsToContact() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPersonToContact(PersonToContact personToContact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePersonToContact(PersonToContact personToContact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePersonToContact(long id) {
		// TODO Auto-generated method stub

	}

}
