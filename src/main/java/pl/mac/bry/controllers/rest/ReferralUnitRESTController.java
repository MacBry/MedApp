package pl.mac.bry.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import pl.mac.bry.entities.ReferralUnit;
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
}
