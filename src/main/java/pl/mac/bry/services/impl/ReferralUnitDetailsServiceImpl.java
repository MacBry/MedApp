package pl.mac.bry.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.audit4j.core.annotation.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.entities.ReferralUnitDetails;
import pl.mac.bry.repositories.ReferralUnitDetailsRepository;
import pl.mac.bry.services.ReferralUnitDetailsService;
import pl.mac.bry.services.ReferralUnitService;

@Service
public class ReferralUnitDetailsServiceImpl implements ReferralUnitDetailsService {

	private ReferralUnitDetailsRepository referralUnitDetailsRepository;
	private ReferralUnitService referralUnitService;


	@Autowired
	public ReferralUnitDetailsServiceImpl(ReferralUnitDetailsRepository referralUnitDetailsRepository,
			ReferralUnitService referralUnitService) {
		super();
		this.referralUnitDetailsRepository = referralUnitDetailsRepository;
		this.referralUnitService = referralUnitService;
	}

	@Override
	@Audit(action = "ReferralUnitDetailsServiceImpl.findReferralUnitDetailsById()")
	public ReferralUnitDetails findReferralUnitDetailsById(long id) {
		return referralUnitDetailsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid id: " + id));
	}

	@Override
	@Audit(action =  "ReferralUnitDetailsServiceImpl.findReferralUnitDetailsByNipNumber()")
	public ReferralUnitDetails findReferralUnitDetailsByNipNumber(long nipNumber) {
		return referralUnitDetailsRepository.findReferralUnitDetailsByNipNumber(nipNumber);
	}

	@Override
	@Audit(action =  "ReferralUnitDetailsServiceImpl.findReferralUnitDetailsByRegonNumber()")
	public ReferralUnitDetails findReferralUnitDetailsByRegonNumber(long regonNumber) {
		return referralUnitDetailsRepository.findReferralUnitDetailsByRegonNumber(regonNumber);
	}

	@Override
	@Audit(action = "ReferralUnitDetailsServiceImpl.findReferralUnitDetailsByPhoneNumber()")
	public Iterable<ReferralUnitDetails> findReferralUnitDetailsByPhoneNumber(String phoneNumber) {
		return referralUnitDetailsRepository.findReferralUnitDetailsByPhoneNumber(phoneNumber);
	}

	@Override
	@Audit(action = "ReferralUnitDetailsServiceImpl.findReferralUnitDetailsByEmail()")
	public Iterable<ReferralUnitDetails> findReferralUnitDetailsByEmail(String email) {
		return referralUnitDetailsRepository.findReferralUnitDetailsByEmail(email);
	}

	@Override
	@Audit(action = "ReferralUnitDetailsServiceImpl.findReferralUnitDetailsByResortBookNumber()")
	public ReferralUnitDetails findReferralUnitDetailsByResortBookNumber(String resortBookNumber) {
		return referralUnitDetailsRepository.findReferralUnitDetailsByResortBookNumber(resortBookNumber);
	}

	@Override
	@Audit(action = "ReferralUnitDetailsServiceImpl.getAllReferralUnitDetailsIterable()")
	public Iterable<ReferralUnitDetails> getAllReferralUnitDetails() {
		return referralUnitDetailsRepository.findAll();
	}

	@Override
	@Audit(action = "ReferralUnitDetailsServiceImpl.getValues()")
	public <T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor) {

		return listToExtractFrom.stream().map(extractor).collect(Collectors.toList());
	}

	@Override
	@Audit(action = "ReferralUnitDetailsServiceImpl.addReferralUnitDetails()")
	public void addReferralUnitDetails(ReferralUnitDetails referralUnitDetails) {
		referralUnitDetailsRepository.save(referralUnitDetails);
	}

	@Override
	@Audit(action = "ReferralUnitDetailsServiceImpl.updateReferralUnitDetails()")
	public void updateReferralUnitDetails(long id, ReferralUnitDetails referralUnitDetails) {
		ReferralUnit ref = referralUnitService.findReferralUnitById(id);
		ref.setReferralUnitDetails(referralUnitDetails);
		referralUnitDetailsRepository.save(referralUnitDetails);
		referralUnitService.addReferralUnit(ref);
	}

	@Override
	@Audit(action = "ReferralUnitDetailsServiceImpl.deleteReferralUnitDetails()")
	public void deleteReferralUnitDetails(long id) {
		Optional<ReferralUnitDetails> opt = referralUnitDetailsRepository.findById(id);
		ReferralUnitDetails details = opt.get();
		long refId = details.getReferralUnit().getId();
		ReferralUnit referralUnit = referralUnitService.findReferralUnitById(refId);
		long detailId = referralUnit.getReferralUnitDetails().getId();
		System.out.println(detailId);
		referralUnit.setReferralUnitDetails(null);
		ReferralUnitDetails referralUnitDetails = findReferralUnitDetailsById(detailId);
		referralUnitDetailsRepository.delete(referralUnitDetails);
	}

	@Override
	@Audit(action = "ReferralUnitDetailsServiceImpl.addDetailToReferralUnit()")
	public void addDetailToReferralUnit(long id, ReferralUnitDetails referralUnitDetails) {
		ReferralUnit referralUnit = referralUnitService.findReferralUnitById(id);
		referralUnitDetailsRepository.save(referralUnitDetails);
		referralUnit.addReferralUnitDetail(referralUnitDetails);
		referralUnitDetails.addReferralUnit(referralUnit);
		referralUnitService.updateReferralUnit(referralUnit);
	}

	@Override
	@Audit(action =  "ReferralUnitDetailsServiceImpl.findReferralDetail()")
	public ReferralUnitDetails findReferralDetails(long referralId) {
		ReferralUnit referralUnit = referralUnitService.findReferralUnitById(referralId);
		return referralUnit.getReferralUnitDetails(); 
	}
	

}
