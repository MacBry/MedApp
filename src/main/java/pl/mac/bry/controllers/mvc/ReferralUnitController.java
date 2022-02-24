package pl.mac.bry.controllers.mvc;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.DocumentException;

import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.services.ReferralUnitService;
import pl.mac.bry.services.impl.PdfRefUnitAddressLabelExporterImpl;

@Controller
public class ReferralUnitController {
	
	private ReferralUnitService referralUnitService;
	private PdfRefUnitAddressLabelExporterImpl exporter;

	@Autowired
	public ReferralUnitController(ReferralUnitService referralUnitService,
			@Qualifier("REF-ADDRESS-LABEL")PdfRefUnitAddressLabelExporterImpl exporter) {
		super();
		this.referralUnitService = referralUnitService;
		this.exporter = exporter;
		
	}
	
	@GetMapping("/show-referral-units")
	public String showAllReferralUnits(Model model) {
		model.addAttribute("referralUnits", referralUnitService.getAllReferralUnits());
		return "show-referral-units";
	}
	
	@GetMapping("/find-referral-unit-by-part-of-full-name")
	public String findReferralUnitByPartOfFullName(Model model, @RequestParam("partOfFullName") String partOffFullName) {
		try {
			model.addAttribute("referralUnits", referralUnitService.findReferralUnitByPartofFullName(partOffFullName));
			return "show-referral-units";
			
		} catch (IllegalArgumentException e) {
			model.addAttribute("", referralUnitService.getAllReferralUnits());
			return "show-referral-units";
		}
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
	
	@GetMapping("/print-ref-label/{id}")
	public void exportLabelToPdf(HttpServletResponse response, @PathVariable("id") long id) throws DocumentException, IOException {
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=sample_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        
        ReferralUnit referralUnit = referralUnitService.findReferralUnitById(id);
        exporter.setReferralUnit(referralUnit);
        exporter.export(response);
	}
}
