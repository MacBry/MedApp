package pl.mac.bry.controllers.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.services.ReferralUnitService;

@Controller
public class ReferralUnitController {
	
	private ReferralUnitService referralUnitService;

	@Autowired
	public ReferralUnitController(ReferralUnitService referralUnitService) {
		super();
		this.referralUnitService = referralUnitService;
	}
	
	@GetMapping("/show-referral-units")
	public String showAllReferralUnits(Model model) {
		model.addAttribute("referralUnits", referralUnitService.getAllReferralUnits());
		return "show-referral-units";
	}
	
	@GetMapping("show-add-referral-unit-form")
	public String showAddReferralUnitForm(ReferralUnit referralUnit) {
		return "add-referral-unit-form";
	}
	
	@PostMapping("/add-referral-unit")
	public String addReferralUnit(@Valid ReferralUnit referralUnit, BindingResult result, Model model) {
		model.addAttribute(referralUnit);
		if(result.hasErrors()) {
			return "add-referral-unit";
		}
		referralUnitService.addReferralUnit(referralUnit);
		return "redirect:/show-referral-units";
	}
	
	@GetMapping("/show-update-referral-unit-form/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		ReferralUnit referralUnit = referralUnitService.findReferralUnitById(id);
		model.addAttribute("referralUnit", referralUnit);
		return "update-referral-unit-form";
	}
	
	@PostMapping("/update-referral-unit/{id}")
	public String updateReferralUnit(@PathVariable("id") long id, @Valid ReferralUnit referralUnit, BindingResult result) {
		if(result.hasErrors()) {
			return "update-referral-unit-form";
		}
		referralUnitService.updateReferralUnit(referralUnit);
		return "redirect:/show-referral-units";
	}
	
	@GetMapping("/delete-referral-unit/{id}")
	public String deleteReferralUnit(@PathVariable("id") long id) {
		referralUnitService.deleteReferralunit(id);
		return "redirect:/show-referral-units";
	}
}
