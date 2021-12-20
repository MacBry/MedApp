package pl.mac.bry.controllers.rest;

import java.time.LocalDateTime;
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

import pl.mac.bry.entities.Sample;
import pl.mac.bry.entities.SampleType;
import pl.mac.bry.services.SampleService;

@RestController
@RequestMapping("/api/samples")
public class SampleRESTController {

	private SampleService sampleService;

	@Autowired
	public SampleRESTController(SampleService sampleService) {
		super();
		this.sampleService = sampleService;
	}
	
	@GetMapping("/")
	public Iterable<Sample> getAllSamples(){
		return sampleService.getAllSamples();
	}
	
	@GetMapping("/id/{id}")
	public Sample getAmpleById (@PathVariable long id) {
		return sampleService.findSampleById(id);
	}
	
	@GetMapping("/id/{id}/donation-date-time")
	public LocalDateTime getSampleDonationDateTime(@PathVariable long id) {
		return sampleService.findSampleById(id).getDonationDateTime();
	}
	
	@GetMapping("/donations-dates-times")
	public List<LocalDateTime> getAllSamplesDonationDatesTimes() {
		List<Sample> allSamples =  (List<Sample>) sampleService.getAllSamples();
		return sampleService.getValues(LocalDateTime.class, allSamples, Sample::getDonationDateTime);
	}
	
	@GetMapping("/id/{id}/sample-rejestration-date-time")
	public LocalDateTime getSampleRejestrationDateTime(@PathVariable long id) {
		return sampleService.findSampleById(id).getRejestrationDateTime();
	}
	
	@GetMapping("rejestration-dates-times")
	public List<LocalDateTime> getAllSamplesRejestrationDateTimes() {
		List<Sample> allSamples =  (List<Sample>) sampleService.getAllSamples();
		return sampleService.getValues(LocalDateTime.class, allSamples, Sample::getRejestrationDateTime);
	}
	
	@GetMapping("/id/{id}/sample-type")
	public SampleType getSampleType(@PathVariable long id) {
		return sampleService.findSampleById(id).getSampleType();
	}
	
	@GetMapping("/samples-types")
	public List<SampleType> getAllSamplesTypes() {
		List<Sample> allSamples =  (List<Sample>) sampleService.getAllSamples();
		return sampleService.getValues(SampleType.class, allSamples, Sample::getSampleType);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveSample(@RequestBody Sample sample) {
		sampleService.addSample(sample);
	}
	
	@PutMapping(consumes =  MediaType.APPLICATION_JSON_VALUE)
	public void updateSample(@RequestBody Sample sample) {
		sampleService.updateSample(sample);
	}
	
	@DeleteMapping("/delete/id/{id}")
	public void deleteSample(@PathVariable long id) {
		sampleService.deleteSample(id);
	}
}
