package pl.mac.bry.controllers.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

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
	
	public String showPersonsToContact (@PathVariable("id")long id, Model model) {
		model.addAttribute("personToContact", personToContactService.findPatientAllPersonsToContact(id));
		this.id = id;
		return "show-persons-to-contact";
	}

}
