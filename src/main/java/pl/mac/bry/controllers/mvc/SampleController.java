package pl.mac.bry.controllers.mvc;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.entities.Sample;
import pl.mac.bry.services.ReferralUnitService;
import pl.mac.bry.services.SampleService;
import pl.mac.bry.util.StringToDateTimeConverter;

@Controller
public class SampleController {
	
	private SampleService sampleService;
	private ReferralUnitService referralUnitService;
	private long patientId;


	@Autowired
	public SampleController(SampleService sampleService,
			ReferralUnitService referralUnitService) {
		super();
		this.sampleService = sampleService;
		this.referralUnitService = referralUnitService;

	}
	
	@GetMapping("/patient-samples/{id}")
	public String showPatientSamples(@PathVariable("id") long id, Model model) {
		model.addAttribute("samples", sampleService.findPatientAllSamples(id));
		this.patientId = id; 
		return "show-patient-samples";
	}
	
	@GetMapping("/find-sample")
	public String findSampleById(Model model, @RequestParam("sampleId") long id) {
		try {
			if(sampleService.findSampleById(id).getPatient()==null) {
				return "show-patient-samples";
			}else if (sampleService.findSampleById(id).getPatient().getId() == this.patientId) {
			model.addAttribute("samples", sampleService.findSampleById(id));
			return "show-patient-samples";
			}
			return "show-patient-samples";
		} catch (IllegalArgumentException ex) {
			return "show-patient-samples";
		}
	}
	
	@GetMapping("/show-add-sample-form")
	public String showAddSampleForm(Sample sample, Model model) {
		List<ReferralUnit> units = (List<ReferralUnit>) referralUnitService.getAllReferralUnits();
		model.addAttribute("units", units);
		return "add-sample-form";
	}
	
	@PostMapping("/add-sample")
	public String addSample(@Valid Sample sample, BindingResult result, Model model,
			@RequestParam(defaultValue = "") String date) {
		
		if(result.hasErrors()) {
			return "add-sample-form";
		}
		sample.setDonationDateTime(StringToDateTimeConverter.convert(date));
		sample.setRejestrationDateTime(LocalDateTime.now());
		model.addAttribute(sample);
		sampleService.addSampleToPatient(patientId, sample);
		model.addAttribute("samples", sampleService.findPatientAllSamples(patientId));
		return "show-patient-samples";
	}
	
	@GetMapping("/show-update-sample-form/{id}")
	public String showUpadateForm(@PathVariable("id") long id, Model model) {
		Sample sample = sampleService.findSampleById(id);
		List<ReferralUnit> units = (List<ReferralUnit>) referralUnitService.getAllReferralUnits();
		model.addAttribute("units", units);
		model.addAttribute("sample", sample);
		return "update-sample";
	}
	
	@PostMapping("/update-sample/{id}")
	public String updatePatientSample(@PathVariable("id")long id, @Valid Sample sample, BindingResult result,
			@RequestParam(defaultValue = "") String date) {
		if(result.hasErrors()) {
			sample.setId(id);
			return "update-sample";
		}
		sample.setDonationDateTime(StringToDateTimeConverter.convert(date));
		sampleService.updatePatientSample(id, sample);
		return "redirect:/patient-samples/{id}";
	}
	
	
	
	@GetMapping("/delete-sample/{id}")
	public String deleteSample (@PathVariable("id") long id) {
		sampleService.deleteSample(id);
		
		return "show-patient-samples";
	}
}
