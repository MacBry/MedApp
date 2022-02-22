package pl.mac.bry.controllers.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.mac.bry.entities.ReferralUnitDetails;
import pl.mac.bry.services.ReferralUnitDetailsService;

@Controller
public class ReferralUnitDetailsController {

	private ReferralUnitDetailsService referralUnitDetailsService;
	private long refId;
	@Autowired
	public ReferralUnitDetailsController(ReferralUnitDetailsService referralUnitDetailsService) {
		super();
		this.referralUnitDetailsService = referralUnitDetailsService;
	}
	
	@GetMapping("/referral-unit-details/{id}")
	public String showReferralUnitDetail(@PathVariable("id") long refId, Model model ) {
		model.addAttribute("details", referralUnitDetailsService.findReferralDetails(refId));
		this.refId = refId;
		return "show-referral-unit-details";
	}
	
	@GetMapping("/show-add-referral-unit-detail-form")
	public String showAddDetailForm(ReferralUnitDetails referralUnitDetails) {
		return "add-referral-unit-detail-form";
	}
	
	@PostMapping("/add-referral-detail")
	public String addRederralUnitDetail(@Valid ReferralUnitDetails referralUnitDetails,BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-referral-unit-detail-form";
		}
		model.addAttribute(referralUnitDetails);
		referralUnitDetailsService.addDetailToReferralUnit(refId, referralUnitDetails);
		model.addAttribute("details",referralUnitDetailsService.findReferralDetails(refId));
		referralUnitDetailsService.findReferralDetails(refId).getId();
		return "show-referral-unit-details";
	}
	
	@GetMapping("show-update-referral-unit-details-form/{id}")
	public String showUpdateReferralUnitDetailForm(@PathVariable("id") long id, Model model) {
		ReferralUnitDetails referralUnitDetails = referralUnitDetailsService.findReferralUnitDetailsById(id);
		model.addAttribute("details", referralUnitDetails);
		return "update-referral-details";
	}
	
	@PostMapping("/update-referral-unit-detail/{id}")
	public String updateReferralUnitDetail(@PathVariable("id") long id, @Valid ReferralUnitDetails referralUnitDetails,
			BindingResult result) {
		if(result.hasErrors() ) {
			referralUnitDetails.setId(id);
			return "update-referral-details";
		}
		referralUnitDetailsService.updateReferralUnitDetails(referralUnitDetails);
		return "redirect:/referral-unit-details/{id}";
	}
	
	@GetMapping("/delete-referral-unit-details/{refId}")
	public String deleteDetails(@PathVariable("refId")long refId ) {
		referralUnitDetailsService.deleteReferralUnitDetails(refId);
		return "redirect:/show-referral-units";
	}
}
