package pl.mac.bry.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import pl.mac.bry.entities.Sample;
import pl.mac.bry.entities.enums.SampleType;

public interface SampleService {
	
	Sample findSampleById(long id);
	
	Iterable<Sample> findSamplesByColectionDateTime (LocalDateTime colectionDateTime);
	
	Iterable<Sample> findSampleBySampleType(SampleType sampleType);
	
	Iterable<Sample> getAllSamples();
	
	void addSample(Sample sample);
	
	void updateSample(Sample sample);
	
	void deleteSample(long id);
	
	<T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor);

	Iterable<Sample> findPatientAllSamples(long patientId);

	void addSampleToPatient(long patientId, Sample sample);

	void updatePatientSample(long patientId, Sample sample);

	void addReferralUnitToSample(long referralUnitId, Sample sample);
}
