package pl.mac.bry.services.impl;

import java.time.LocalDateTime;

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
	public Sample findSampleById(long id) {
		return  sampleRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Sample id:" + id));
	}

	@Override
	public Iterable<Sample> findSamplesByColectionDateTime(LocalDateTime colectionDateTime) {
		return sampleRepository.findByColectionDateTime(colectionDateTime);
	}

	@Override
	public Iterable<Sample> findSampleBySampleType(SampleType sampleType) {
		return sampleRepository.findBySampleType(sampleType);
	}

	@Override
	public Iterable<Sample> getAllSamples() {
		return sampleRepository.findAll();
	}

	@Override
	public void addSample(Sample sample) {
		sampleRepository.save(sample);
	}

	@Override
	public void updateSample(Sample sample) {
		sampleRepository.save(sample);
	}

	@Override
	public void deleteSample(long id) {
		Sample sample = findSampleById(id);
		sampleRepository.delete(sample);
	}

}
