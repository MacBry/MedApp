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
public class ReferralunitDetailsController {

	private ReferralUnitDetailsService referralUnitDetailsService;
	private long id;
	
	@Autowired
	public ReferralunitDetailsController(ReferralUnitDetailsService referralUnitDetailsService) {
		super();
		this.referralUnitDetailsService = referralUnitDetailsService;
	}
	
	@GetMapping("/referral-unit-details/{id}")
	public String showReferralUnitDetail(@PathVariable("id") long id, Model model ) {
		model.addAttribute("details", referralUnitDetailsService.findReferralUnitDetailsById(id));
		this.id = id;
		return "show-referral-unit-details";
	}
	
	@GetMapping("/show-add-referral-unit-detail-form")
	public String showAddDetailForm(ReferralUnitDetails referralUnitDetails) {
		return "add-referral-unit-detail-form";
	}
	
	@PostMapping("/add-referral-detail")
	public String addDetail(@Valid ReferralUnitDetails referralUnitDetails,BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-referral-unit-detail-form";
		}
		model.addAttribute(referralUnitDetails);
		referralUnitDetailsService.addDetailToReferralUnit(id, referralUnitDetails);
		model.addAttribute("details",referralUnitDetailsService.findReferralUnitDetailsById(id));
		return "show-referral-unit-details";
	}
	
	@GetMapping("show-update-details-form/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
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
	
	@GetMapping("/delete-referral-unit-details/{id}")
	public String deleteDetails(@PathVariable("id")long id) {
		referralUnitDetailsService.deleteReferralUnitDetails(id);
		return "redirect:/referral-unit-details/{id}";
	}
}
