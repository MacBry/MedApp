package pl.mac.bry.services;

import pl.mac.bry.entities.ReferralUnitDetails;

public interface ReferralUnitDetailsService {
	
	ReferralUnitDetails findReferralUnitDetailsById(long id);
	
	ReferralUnitDetails findReferralUnitDetailsByNipNumber(long nipNumber);
	
	ReferralUnitDetails findReferralUnitDetailsByRegonNumber(long regonNumber);
	
	Iterable<ReferralUnitDetails> findReferralUnitDetailsByPhoneNumber(String phoneNumber);
	
	Iterable<ReferralUnitDetails> findReferralUnitDetailsByEmail(String email);
	
	ReferralUnitDetails findReferralUnitDetailsByResortBookNumber(String resortBookNumber);
}
