package pl.mac.bry.services;

import java.util.List;
import java.util.function.Function;

import pl.mac.bry.entities.Address;

public interface AddressService {
	
	Address findAddressById(long id);
	
	Iterable<Address> findAddressByCountry(String country);
	
	Iterable<Address> findAddressByCity(String city);
	
	Iterable<Address> findAddressByStreet(String street);
	
	Iterable<Address> findAddressByZipCode(String zipCode);
	
	Iterable<Address> getAllAdresses();
	
	void addAddresss(Address address);
	
	void updateAddress(Address address);
	
	void deleteAddress(long id);
	
	<T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor);
	

}
