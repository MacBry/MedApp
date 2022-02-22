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

import pl.mac.bry.entities.ReferralUnitDetails;
import pl.mac.bry.services.ReferralUnitDetailsService;

@RestController
@RequestMapping("/api/referral-unit-detail")
public class ReferralUnitDetailsRESTController {
	
	private ReferralUnitDetailsService referralUnitDetailsService;

	@Autowired
	public ReferralUnitDetailsRESTController(ReferralUnitDetailsService referralUnitDetailsService) {
		super();
		this.referralUnitDetailsService = referralUnitDetailsService;
	}
	
	@GetMapping("/")
	public Iterable<ReferralUnitDetails> getAllReferralUnitDetails () {
		return referralUnitDetailsService.getAllReferralUnitDetails();
	}
	
	@GetMapping("/id/{id}")
	public ReferralUnitDetails getReferralUnitDetailsById(@PathVariable long id) {
		return referralUnitDetailsService.findReferralUnitDetailsById(id);
	}
	
	@GetMapping("/id/{id}/nip-number")
	public long getReferralUnitDetailsNipNumber(@PathVariable long id) {
		return referralUnitDetailsService.findReferralUnitDetailsById(id).getNipNumber();
	} 
	
	@GetMapping("/nip-numbers")
	public List<Long> getReferralUnitsDetailsAllNipNumbers() {
		List<ReferralUnitDetails> referralUnitDetails = (List<ReferralUnitDetails>)referralUnitDetailsService.getAllReferralUnitDetails();
		return referralUnitDetailsService.getValues(Long.class, referralUnitDetails, ReferralUnitDetails::getNipNumber);
	}
	
	@GetMapping("/id/{id}/regon-number")
	public long getReferralUnitDetailsRegonNumber(@PathVariable long id) {
		return referralUnitDetailsService.findReferralUnitDetailsById(id).getRegonNumber();
	}
	
	@GetMapping("/regon-numbers")
	public List<Long> getReferralUnitsDetailsAllRegonNumbers() {
		List<ReferralUnitDetails> referralUnitDetails = (List<ReferralUnitDetails>)referralUnitDetailsService.getAllReferralUnitDetails();
		return referralUnitDetailsService.getValues(Long.class, referralUnitDetails, ReferralUnitDetails::getRegonNumber);
	}
	
	@GetMapping("/id/{id}/phone-number")
	public String getReferralUnitDetailsPhoneNumber(@PathVariable long id) {
		return referralUnitDetailsService.findReferralUnitDetailsById(id).getPhoneNumber();
	}
	
	@GetMapping("/phone-numbers")
	public List<String> getReferralUnitsDetailsAllPhoneNumbers() {
		List<ReferralUnitDetails> referralUnitDetails = (List<ReferralUnitDetails>)referralUnitDetailsService.getAllReferralUnitDetails();
		return referralUnitDetailsService.getValues(String.class, referralUnitDetails, ReferralUnitDetails::getPhoneNumber);
	}
	
	@GetMapping("/id/{id}/email")
	public String getReferralunitDetailsEmailString (@PathVariable long id) {
		return referralUnitDetailsService.findReferralUnitDetailsById(id).getEmail();
	}
	
	@GetMapping("/emails")
	public List<String> getReferralUnitDetailsAllEmails() {
		List<ReferralUnitDetails> referralUnitDetails = (List<ReferralUnitDetails>) referralUnitDetailsService.getAllReferralUnitDetails();
		return referralUnitDetailsService.getValues(String.class, referralUnitDetails, ReferralUnitDetails::getEmail);
	}
	
	@GetMapping("/id/{id}/resort-book-number")
	public String getReferralUnitDetailsResortBookNumber(@PathVariable long id) {
		return referralUnitDetailsService.findReferralUnitDetailsById(id).getResortBookNumber();
	}
	
	@GetMapping("/resort-book-numbers")
	public List<String> getReferralUnitDetailsAllResortBookNumbers() {
		List<ReferralUnitDetails> referralUnitDetails = (List<ReferralUnitDetails>) referralUnitDetailsService.getAllReferralUnitDetails();
		return referralUnitDetailsService.getValues(String.class, referralUnitDetails, ReferralUnitDetails::getResortBookNumber);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveReferralUnitDetails(@RequestBody ReferralUnitDetails referralUnitDetails ) {
		referralUnitDetailsService.addReferralUnitDetails(referralUnitDetails);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateReferralUnitDetails(@RequestBody long id,@RequestBody ReferralUnitDetails referralUnitDetails) {
		referralUnitDetailsService.updateReferralUnitDetails(id, referralUnitDetails);
	}
	
	@DeleteMapping("/delete/id/{id}")
	public void deleteReferralUnitDetails(@PathVariable long id) {
		referralUnitDetailsService.deleteReferralUnitDetails(id);
	}
}
