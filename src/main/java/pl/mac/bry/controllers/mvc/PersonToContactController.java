package pl.mac.bry.controllers.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.mac.bry.entities.PersonToContact;
import pl.mac.bry.services.PersonToContactService;

@Controller
public class PersonToContactController {
	
	private PersonToContactService personToContactService;
	private long id;

	@Autowired
	public PersonToContactController(PersonToContactService personToContactService) {
		super();
		this.personToContactService = personToContactService;
	}
	
	@GetMapping("patient-persons-to-contact/{id}")
	public String showPersonsToContact (@PathVariable("id")long id, Model model) {
		model.addAttribute("personsToContact", personToContactService.findPatientAllPersonsToContact(id));
		this.id = id;
		return "show-persons-to-contact";
	}
	
	@GetMapping("/show-add-person-to-contatct-form")
	public String showAddForm(PersonToContact personToContact) {
		return "add-person-to-contatct-form";
	}
	
	@PostMapping("/add-person-to-contatct")
	public String addPersonToContact(@Valid PersonToContact personToContact, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add-person-to-contatct-form";
		}
		model.addAttribute(personToContact);
		personToContactService.addPersonToContactToPatientDetails(id, personToContact);
		model.addAttribute("personsToContact", personToContactService.findPatientAllPersonsToContact(id));
		return "show-persons-to-contact";
	}
	
	@GetMapping("show-update-person-to-contact-form/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		PersonToContact personToContact = personToContactService.findPersonToContactById(id);
		model.addAttribute("personToContact", personToContact);
		return "update-person-to-contact";
	}
	
	@PostMapping("/update-person-to-contact/{id}")
	public String updatePersonToContact(@PathVariable("id") long id, @Valid PersonToContact personToContact, BindingResult result) {
		if(result.hasErrors()) {
			personToContact.setId(id);
			return "update-person-to-contact";
		}
		personToContactService.updatePersonToContact(id,personToContact);
		return "redirect:/patient-persons-to-contact/{id}";
	}
	
	@GetMapping("delete-person-to-contact/{id}")
	public String deletePersontoContact(@PathVariable("id") long id) {
		personToContactService.deletePersonToContact(id);
		return"redirect:/patient-persons-to-contact/{id}";
	}

}
