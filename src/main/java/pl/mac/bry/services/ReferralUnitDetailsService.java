package pl.mac.bry.services;

import java.util.List;
import java.util.function.Function;

import pl.mac.bry.entities.ReferralUnitDetails;

public interface ReferralUnitDetailsService {
	
	ReferralUnitDetails findReferralUnitDetailsById(long referralUnitId);
	
	ReferralUnitDetails findReferralUnitDetailsByNipNumber(long nipNumber);
	
	ReferralUnitDetails findReferralUnitDetailsByRegonNumber(long regonNumber);
	
	Iterable<ReferralUnitDetails> findReferralUnitDetailsByPhoneNumber(String phoneNumber);
	
	Iterable<ReferralUnitDetails> findReferralUnitDetailsByEmail(String email);
	
	ReferralUnitDetails findReferralUnitDetailsByResortBookNumber(String resortBookNumber);
	
	Iterable<ReferralUnitDetails> getAllReferralUnitDetails();
	
	<T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor);
	
	void addReferralUnitDetails(ReferralUnitDetails referralUnitDetails);
	
	void updateReferralUnitDetails(ReferralUnitDetails referralUnitDetails);
	
	void deleteReferralUnitDetails(long id);

	void addDetailToReferralUnit(long id, ReferralUnitDetails referralUnitDetails);
}
