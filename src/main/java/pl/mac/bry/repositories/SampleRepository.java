package pl.mac.bry.repositories;

import java.time.LocalDateTime;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.Sample;
import pl.mac.bry.entities.SampleType;

public interface SampleRepository extends CrudRepository<Sample, Long> {
	
	Iterable<Sample> findByDonationDateTime(LocalDateTime colectionDateTime);
	
	Iterable<Sample> findBySampleType(SampleType sampleType);
	
}
