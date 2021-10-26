package pl.mac.bry.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.Sample;

public interface SampleRepository extends CrudRepository<Sample, Long> {

}
