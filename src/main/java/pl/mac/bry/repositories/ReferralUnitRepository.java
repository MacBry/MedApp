package pl.mac.bry.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.ReferralUnit;

public interface ReferralUnitRepository extends CrudRepository<ReferralUnit,Long> {

	ReferralUnit findReferralUnitByFullName(String fullName);

	ReferralUnit findReferralUnitByShortName(String shortName);

}
