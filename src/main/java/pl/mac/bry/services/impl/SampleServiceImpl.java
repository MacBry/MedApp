package pl.mac.bry.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.audit4j.core.annotation.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mac.bry.entities.Patient;
import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.entities.Sample;
import pl.mac.bry.entities.dto.SampleDTOMapper;
import pl.mac.bry.entities.enums.SampleType;
import pl.mac.bry.repositories.SampleRepository;
import pl.mac.bry.services.PatientService;
import pl.mac.bry.services.ReferralUnitService;
import pl.mac.bry.services.SampleService;

@Service
public class SampleServiceImpl implements SampleService {
	
	private SampleRepository sampleRepository;
	private PatientService patientService;
	private ReferralUnitService referralUnitService;
	private SampleDTOMapper sampleDTOMapper;
	
	@Autowired
	public SampleServiceImpl(SampleRepository sampleRepository,
			PatientService patientService,
			ReferralUnitService referralUnitService,
			SampleDTOMapper sampleDTOMapper) {
		super();
		this.sampleRepository = sampleRepository;
		this.patientService = patientService;
		this.referralUnitService = referralUnitService;
		this.sampleDTOMapper = sampleDTOMapper;
	}

	@Override
	@Audit(action = "SampleServiceImpl.findSampleById()")
	public Sample findSampleById(long id) {
		return  sampleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Sample id:" + id));
	}

	@Override
	@Audit(action = "SampleServiceImpl.findSamplesByColectionDateTime()")
	public Iterable<Sample> findSamplesByColectionDateTime(LocalDateTime colectionDateTime) {
		return sampleRepository.findByDonationDateTime(colectionDateTime);
	}

	@Override
	@Audit(action = "SampleServiceImpl.findSampleBySampleType()")
	public Iterable<Sample> findSampleBySampleType(SampleType sampleType) {
		return sampleRepository.findBySampleType(sampleType);
	}

	@Override
	@Audit(action = "SampleServiceImpl.getAllSamples()")
	public Iterable<Sample> getAllSamples() {
		return sampleRepository.findAll();
	}

	@Override
	@Audit(action = "SampleServiceImpl.addSample()")
	public void addSample(Sample sample) {
		sampleRepository.save(sample);
	}

	@Override
	@Audit(action = "SampleServiceImpl.updateSample()")
	public void updateSample(Sample sample) {
		sampleRepository.save(sample);
	}

	@Override
	@Audit(action = "SampleServiceImpl.deleteSample()")
	public void deleteSample(long id) {
		Sample sample = findSampleById(id);
		Patient patient = patientService.findPatientById(sample.getPatient().getId());
		sample.setPatient(null);
		sampleRepository.delete(sample);
		patientService.updatePatient(patient);
	}

	@Override
	@Audit(action = "SampleServiceImpl.getValues()")
	public <T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor) {
	    return listToExtractFrom.stream().map(extractor).collect(Collectors.toList());
	  }
	
	@Override
	@Audit(action = "SampleServiceImpl.findPatientAllSamples()")
	public Iterable<Sample> findPatientAllSamples(long patientId) {
		Patient patient = patientService.findPatientById(patientId);
		return patient.getPatientSamples();
	}

	@Override
	@Audit(action = "SampleServiceImpl.addSampleToPatient()") 
	public void addSampleToPatient(long patientId, Sample sample) {
		Patient patient = patientService.findPatientById(patientId);
		sample.setPatient(patient);
		patient.addPatientSample(sample);
		sampleRepository.save(sample);
	}
	
	@Override
	@Audit(action =  "SampleServiceImpl.addReferralUnitToSample()")
	public void addReferralUnitToSample(long referralUnitId, Sample sample) {
		ReferralUnit referralUnit = referralUnitService.findReferralUnitById(referralUnitId);
		sample.setReferralUnit(referralUnit);
		sampleRepository.save(sample);
	}

	@Override
	@Audit(action = "SampleServiceImpl.updatePatientSample()")
	public void updatePatientSample(long patientId, Sample sample) {
		Patient patient = patientService.findPatientById(patientId);
		sample.setPatient(patient);
		sampleRepository.save(sample);
		patientService.updatePatient(patient);
	}
	

}
