package pl.mac.bry.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mac.bry.entities.Address;
import pl.mac.bry.repositories.AddressRepository;
import pl.mac.bry.services.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	private AddressRepository addressRepository;
	
	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository) {
		super();
		this.addressRepository = addressRepository;
	}

	@Override
	public Address findAddressById(long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid adress id: " +  id));
	}

	@Override
	public Iterable<Address> findAddressByCountry(String country) {
		return addressRepository.findByCountry(country);
	}

	@Override
	public Iterable<Address> findAddressByCity(String city) {
		return addressRepository.findByCity(city);
	}

	@Override
	public Iterable<Address> findAddressByStreet(String street) {
		return addressRepository.findByStreet(street);
	}

	@Override
	public Iterable<Address> findAddressByZipCode(String zipCode) {
		return addressRepository.findByZipCode(zipCode);
	}

	@Override
	public Iterable<Address> getAllAdresses() {
		return addressRepository.findAll();
	}

	@Override
	public void addAddresss(Address address) {
		addressRepository.save(address);
	}

	@Override
	public void updateAddress(Address address) {
		addressRepository.save(address);
	}

	@Override
	public void deleteAddress(long id) {
		Address address = findAddressById(id);
		addressRepository.delete(address);
	}

}
