package pl.mac.bry.services;

import java.util.List;
import java.util.function.Function;

import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.entities.enums.Deadline;

public interface ReferralUnitService {
	
	ReferralUnit findReferralUnitById(long id);
	
	ReferralUnit findReferralUnitByFullName(String fullName);
	
	ReferralUnit finReferralUnitByShortName(String shortName);
	
	Iterable<ReferralUnit> getAllReferralUnits();
	
	void addReferralUnit (ReferralUnit referralUnit);
	
	void updateReferralUnit (ReferralUnit referralUnit);
	
	void deleteReferralunit (long id);
	
	Iterable<ReferralUnit> findReferralUnitByPartofFullName(String partOfFullName);
	
	<T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor);

	Iterable<ReferralUnit> findReferralUnitByDeadline(Deadline deadline);
	
}
