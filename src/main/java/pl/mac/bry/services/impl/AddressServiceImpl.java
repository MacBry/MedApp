package pl.mac.bry.services.impl;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.audit4j.core.annotation.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mac.bry.entities.Address;
import pl.mac.bry.entities.Patient;
import pl.mac.bry.repositories.AddressRepository;
import pl.mac.bry.services.AddressService;
import pl.mac.bry.services.PatientService;

@Service
public class AddressServiceImpl implements AddressService {
	
	private AddressRepository addressRepository;
	private PatientService patientService;
	
	@Autowired
	public AddressServiceImpl(AddressRepository addressRepository, PatientService patientService) {
		super();
		this.addressRepository = addressRepository;
		this.patientService = patientService;
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
		Patient patient = patientService.findPatientById(address.getPatientDetails().getId());
		address.setPatientDetails(null);
		addressRepository.delete(address);
		patientService.updatePatient(patient);
	}
	
	@Override
	@Audit(action = "AddressServiceImpl.getValues()")
	public <T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor) {
		return listToExtractFrom.stream().map(extractor).collect(Collectors.toList());
	}

	@Override
	@Audit(action = "AddressServiceImpl.findPatientAllAddresses()")
	public Iterable<Address> findPatientAllAddresses(long patientId) {
		Patient patient = patientService.findPatientById(patientId);
		return patient.getPatientDetails().getPatientAdresses();
	}

	@Override
	@Audit(action = "AddressServiceImpl.addAddressToPatientDetails()")
	public void addAddressToPatientDetails(long patientId, @Valid Address address) {
		Patient patient = patientService.findPatientById(patientId);
		address.setPatientDetails(patient.getPatientDetails());
		patient.getPatientDetails().addPatientAddress(address);
		addressRepository.save(address);
		patientService.updatePatient(patient);
	}

	@Override
	@Audit(action = "AddressServiceImpl.updateAddress()")
	public void updateAddress(long patientId, @Valid Address address) {
		Patient patient = patientService.findPatientById(patientId);
		address.setPatientDetails(patient.getPatientDetails());
		addressRepository.save(address);
		patientService.updatePatient(patient);
	}

}
