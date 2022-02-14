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
import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.entities.ReferralUnitDetails;
import pl.mac.bry.services.ReferralUnitService;

@RestController
@RequestMapping("/api/referral-units")
public class ReferralUnitRESTController {
	
	private ReferralUnitService referralUnitService;

	@Autowired
	public ReferralUnitRESTController(ReferralUnitService referralUnitService) {
		super();
		this.referralUnitService = referralUnitService;
	}
	
	@GetMapping("/")
	public Iterable<ReferralUnit> getAllReferralUnits() {
		return referralUnitService.getAllReferralUnits();
	}
	
	@GetMapping("/id/{id}")
	public ReferralUnit getReferralUnitById(@PathVariable long id) {
		return referralUnitService.findReferralUnitById(id);
	}
	
	@GetMapping("/id/{id}/full-name")
	public String getReferralUnitFullName(@PathVariable long id) {
		return referralUnitService.findReferralUnitById(id).getFullName();
	}
	
	@GetMapping("/full-names")
	public List<String> getAllReferralUnitsFullNames() {
		List<ReferralUnit> allReferralUnitsList = (List<ReferralUnit>)referralUnitService.getAllReferralUnits();
		return referralUnitService.getValues(String.class, allReferralUnitsList, ReferralUnit::getFullName);
	}

	@GetMapping("/id/{id}/short-name")
	public String getReferralunitShortName(@PathVariable long id) {
		return referralUnitService.findReferralUnitById(id).getShortName();
	}
	
	@GetMapping("/short-names")
	public List<String> getAllReferralUnitsShortNames() {
		List<ReferralUnit> allReferralUnitsList = (List<ReferralUnit>)referralUnitService.getAllReferralUnits();
		return referralUnitService.getValues(String.class, allReferralUnitsList, ReferralUnit::getShortName);
	}
	
	@GetMapping("/id/{id}/address")
	public Address getReferralUnitAddress(@PathVariable long id) {
		return referralUnitService.findReferralUnitById(id).getAddress();
	}
	
	@GetMapping("/addresses")
	public List<Address> getAllReferralUnitsAddresses() {
		List<ReferralUnit> allReferralUnitsList = (List<ReferralUnit>)referralUnitService.getAllReferralUnits();
		return referralUnitService.getValues(Address.class, allReferralUnitsList, ReferralUnit::getAddress);
	}
	
	@GetMapping("/id/{id}/detail")
	public ReferralUnitDetails getReferralUnitDetails(@PathVariable long id) {
		return referralUnitService.findReferralUnitById(id).getReferralUnitDetails();
	}
	
	@GetMapping("/details")
	public List<ReferralUnitDetails> getAllReferralUnitsDetails () {
		List<ReferralUnit> allReferralUnitsList = (List<ReferralUnit>)referralUnitService.getAllReferralUnits();
		return referralUnitService.getValues(ReferralUnitDetails.class, allReferralUnitsList, ReferralUnit::getReferralUnitDetails);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveReferralUnit(@RequestBody ReferralUnit referralUnit) {
		referralUnitService.addReferralUnit(referralUnit);
	}
	
	@PutMapping(consumes =  MediaType.APPLICATION_JSON_VALUE)
	public void updateReferralUnit(@RequestBody ReferralUnit referralUnit) {
		referralUnitService.updateReferralUnit(referralUnit);
	}
	
	@DeleteMapping("/delete/id/{id}")
	public void deleteReferralUnit(@PathVariable long id) {
		referralUnitService.deleteReferralunit(id);
	}
}

