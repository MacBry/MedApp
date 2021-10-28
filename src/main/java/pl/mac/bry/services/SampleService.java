package pl.mac.bry.services;

import java.time.LocalDateTime;

import pl.mac.bry.entities.Sample;
import pl.mac.bry.entities.SampleType;

public interface SampleService {
	
	Sample findSampleById(long id);
	
	Iterable<Sample> findSamplesByColectionDateTime (LocalDateTime colectionDateTime);
	
	Iterable<Sample> findSampleBySampleType(SampleType sampleType);
	
	Iterable<Sample> getAllSamples();
	
	void addSample(Sample sample);
	
	void updateSample(Sample sample);
	
	void deleteSample(long id);
}
