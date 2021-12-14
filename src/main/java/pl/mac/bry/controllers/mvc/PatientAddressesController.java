package pl.mac.bry.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.mac.bry.entities.Address;
import pl.mac.bry.services.AddressService;

@Controller
public class PatientAddressesController {
	
	private AddressService addressService;
	private long  id;
	
	public PatientAddressesController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}
	
	@GetMapping("/patient-addresses/{id}")
	public String showPatientAddresses(@PathVariable("id") long id, Model model) {
		model.addAttribute("adresses", addressService.findPatientAllAddresses(id));
		this.id = id;
		return "show-patient-addresses";
	}
	
	@GetMapping("/show-add-address-form")
	public String showAddAddressForm(Address address) {
		return "add-address-form";
	}
	
}
