package pl.mac.bry.controllers.rest;

import java.util.List;

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

import pl.mac.bry.entities.Address;
import pl.mac.bry.services.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressRESTController {

	private AddressService addressService;

	@Autowired
	public AddressRESTController(AddressService addressService) {
		super();
		this.addressService = addressService;
	}
	
	@GetMapping("/")
	public Iterable<Address> getAllAdresses() {
		return addressService.getAllAdresses();
	}
	
	@GetMapping("/id/{id}")
	public Address getAddressById(@PathVariable long id) {
		return addressService.findAddressById(id);
	}
	
	@GetMapping("/id/{id}/country")
	public String getAddressCountry(@PathVariable long id) {
		return addressService.findAddressById(id).getCountry();
	}
	
	@GetMapping("/contrys")
	public List<String> getAllAdressesContrys() {
		List <Address> allAdresses = (List<Address>) addressService.getAllAdresses();
		return addressService.getValues(String.class, allAdresses, Address::getCountry);
	}
	
	@GetMapping("/id/{id}/city")
	public String getAddressCity(@PathVariable long id) {
		return addressService.findAddressById(id).getCity();
	}
	
	@GetMapping("/citys")
	public List<String> getAllAdressesCitys() {
		List <Address> allAdresses = (List<Address>) addressService.getAllAdresses();
		return addressService.getValues(String.class, allAdresses, Address::getCity);
	}
	
	@GetMapping("/id/{id}/street")
	public String getAddressStreet(@PathVariable long id) {
		return addressService.findAddressById(id).getStreet();
	}
	
	@GetMapping("/streets")
	public List<String> getAllAdressesStreets() {
		List <Address> allAdresses = (List<Address>) addressService.getAllAdresses();
		return addressService.getValues(String.class, allAdresses, Address::getStreet);
	}
	
	@GetMapping("/id/{is}/zip-code")
	public String getAddressZipCode(@PathVariable long id) {
		return addressService.findAddressById(id).getZipCode();
	}
	
	@GetMapping("/zip-codes")
	public List<String> getAllAdressesZipCodes() {
		List <Address> allAdresses = (List<Address>) addressService.getAllAdresses();
		return addressService.getValues(String.class, allAdresses, Address::getZipCode);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveAddress(@RequestBody Address address) {
		addressService.addAddresss(address);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateAddress(@RequestBody Address address) {
		addressService.updateAddress(address);
	}
	
	@DeleteMapping("/delete/id/{id}")
	public void deleteAddress(@PathVariable long id) {
		addressService.deleteAddress(id);
	}
}
