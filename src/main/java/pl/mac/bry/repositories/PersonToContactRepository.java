package pl.mac.bry.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.PersonToContact;

public interface PersonToContactRepository extends CrudRepository<PersonToContact, Long> {

}
