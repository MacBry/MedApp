package pl.mac.bry.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.ReferralUnitDetails;

public interface ReferralUnitDetailsRepository extends CrudRepository<ReferralUnitDetails, Long> {

	ReferralUnitDetails findReferralUnitDetailsByNipNumber(long nipNumber);

	ReferralUnitDetails findReferralUnitDetailsByRegonNumber(long regonNumber);

	Iterable<ReferralUnitDetails> findReferralUnitDetailsByPhoneNumber(String phoneNumber);

	Iterable<ReferralUnitDetails> findReferralUnitDetailsByEmail(String email);

	ReferralUnitDetails findReferralUnitDetailsByResortBookNumber(String resortBookNumber);

}
