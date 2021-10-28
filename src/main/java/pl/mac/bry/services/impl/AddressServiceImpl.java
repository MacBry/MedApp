package pl.mac.bry.services.impl;

import org.audit4j.core.annotation.Audit;
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
	@Audit(action = "AddressServiceImpl.findAddressById()")
	public Address findAddressById(long id) {
		return addressRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid adress id: " +  id));
	}

	@Override
	@Audit(action = "AddressServiceImpl.findAddressByCountry()")
	public Iterable<Address> findAddressByCountry(String country) {
		return addressRepository.findByCountry(country);
	}

	@Override
	@Audit(action = "AddressServiceImpl.findAddressByCity()")
	public Iterable<Address> findAddressByCity(String city) {
		return addressRepository.findByCity(city);
	}

	@Override
	@Audit(action = "AddressServiceImpl.findAddressByStreet()")
	public Iterable<Address> findAddressByStreet(String street) {
		return addressRepository.findByStreet(street);
	}

	@Override
	@Audit(action = "AddressServiceImpl.findAddressByZipCode()")
	public Iterable<Address> findAddressByZipCode(String zipCode) {
		return addressRepository.findByZipCode(zipCode);
	}

	@Override@Audit(action = "AddressServiceImpl.getAllAdresses()")
	public Iterable<Address> getAllAdresses() {
		return addressRepository.findAll();
	}

	@Override
	@Audit(action = "AddressServiceImpl.addAddresss()")
	public void addAddresss(Address address) {
		addressRepository.save(address);
	}

	@Override
	@Audit(action = "AddressServiceImpl.updateAddress()")
	public void updateAddress(Address address) {
		addressRepository.save(address);
	}

	@Override
	@Audit(action = "AddressServiceImpl.deleteAddress()")
	public void deleteAddress(long id) {
		Address address = findAddressById(id);
		addressRepository.delete(address);
	}

}
