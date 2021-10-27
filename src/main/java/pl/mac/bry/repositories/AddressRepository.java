package pl.mac.bry.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {
	
	Iterable<Address> findByCountry(String country);
	
	Iterable<Address> findByCity(String city);
	
	Iterable<Address> findByStreet(String street);
	
	Iterable<Address> findByZipCode(String zipCode);

}
