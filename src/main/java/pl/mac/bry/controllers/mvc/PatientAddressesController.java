package pl.mac.bry.controllers.mvc;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	public String showAddressForm(Address address) {
		return "add-address-form";
	}
	
	@PostMapping("add-address")
	public String addAddress(@Valid Address address, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "add-address-form";
		}
		model.addAttribute(address);
		addressService.addAddressToPatientDetails(id, address);
		model.addAttribute("address", addressService.findPatientAllAddresses(id));
		return "show-patient-addresses";
	}
}
