package pl.mac.bry.services;

import java.util.List;
import java.util.function.Function;

import javax.validation.Valid;

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

	Iterable<Address>findPatientAllAddresses(long patientId);

	void addAddressToPatientDetails(long patientId, @Valid Address address);

	void updateAddress(long patientId, @Valid Address address);
	

}
