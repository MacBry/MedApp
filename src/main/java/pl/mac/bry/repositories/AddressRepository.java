package pl.mac.bry.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
