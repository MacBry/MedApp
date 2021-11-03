package pl.mac.bry.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.audit4j.core.annotation.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mac.bry.entities.Sample;
import pl.mac.bry.entities.SampleType;
import pl.mac.bry.repositories.SampleRepository;
import pl.mac.bry.services.SampleService;

@Service
public class SampleServiceImpl implements SampleService {
	
	private SampleRepository sampleRepository;
	
	@Autowired
	public SampleServiceImpl(SampleRepository sampleRepository) {
		super();
		this.sampleRepository = sampleRepository;
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
		sampleRepository.delete(sample);
	}

	@Override
	@Audit(action = "SampleServiceImpl.getValues()")
	public <T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor) {
	    return listToExtractFrom.stream().map(extractor).collect(Collectors.toList());
	  }

}
