package pl.mac.bry.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.mac.bry.entities.PersonToContact;
import pl.mac.bry.services.PersonToContactService;

@RestController
@RequestMapping("/api/person-to-contact")
public class PersonToContactRESTController {

	private PersonToContactService personToContactService;

	@Autowired
	public PersonToContactRESTController(PersonToContactService personToContactService) {
		super();
		this.personToContactService = personToContactService;
	}
	
	@GetMapping("/")
	public Iterable<PersonToContact> getAllPersonsToContact() {
		return personToContactService.getAllPersonsToContact();
	}
	
	@GetMapping("/first-name/{firstName}")
	public Iterable<PersonToContact> getPersonsToContactByFirstName(@PathVariable String firstName) {
		return personToContactService.findPersonToContactByFirstName(firstName);
	}
	
	@GetMapping("/last-name/{lastName}")
	public Iterable<PersonToContact> getPersonsToContactByLastName(@PathVariable String lastName) {
		return personToContactService.findPersonToContactByLastName(lastName);
	}
	
	@GetMapping("/first-name/{firstName}/last-name/{lastName}")
	public Iterable<PersonToContact> getPersonsToContactByFirstNameAndLastName(@PathVariable String firstName,
			@PathVariable String lastName) {
		return personToContactService.findPersonToContactByFirstNameAndLastName(firstName, lastName);
	}
	
	@GetMapping("/email/{email}")
	public Iterable<PersonToContact> getPersonsToContactByEmail(@PathVariable String email){
		return personToContactService.findPersonToContactByEmail(email);
	}
	
	@GetMapping("/phone-number/{phoneNumber}")
	public Iterable<PersonToContact> getPersonToContactByPhoneNumber(@PathVariable String phoneNumber) {
		return personToContactService.findPersonToContactByPhoneNumber(phoneNumber);
	}
	
	@GetMapping("/id/{id}")
	public PersonToContact getPersonToContactById(@PathVariable long id) {
		return personToContactService.findPersonToContactById(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void savePersonToContact(@RequestBody PersonToContact personToContact) {
		personToContactService.addPersonToContact(personToContact);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePatient(@RequestBody PersonToContact personToContact) {
		personToContactService.updatePersonToContact(personToContact);
	}
	
	@DeleteMapping("/delete/id/{id}")
	public void deletePersonToContact(@PathVariable long id) {
		personToContactService.deletePersonToContact(id);
	}
}
