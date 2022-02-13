package pl.mac.bry.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.audit4j.core.annotation.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;

import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.repositories.ReferralUnitRepository;
import pl.mac.bry.services.ReferralUnitService;

@Service
public class ReferralUnitServiceImpl implements ReferralUnitService {

	private ReferralUnitRepository referralUnitRepository;
	
	@Autowired
	public ReferralUnitServiceImpl(ReferralUnitRepository referralUnitRepository) {
		super();
		this.referralUnitRepository = referralUnitRepository;
	}

	@Override
	@Audit(action = "ReferralUnitServiceImpl.findReferralUnitById()")
	public ReferralUnit findReferralUnitById(long id) {
		return referralUnitRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid patient id: " + id));
	}

	@Override
	@Audit(action = "ReferralUnitServiceImpl.findReferralUnitByFullName()")
	public ReferralUnit findReferralUnitByFullName(String fullName) {
		return referralUnitRepository.findReferralUnitByFullName (fullName);
	}

	@Override
	@Audit(action = "ReferralUnitServiceImpl.finReferralUnitByShortName()")
	public ReferralUnit finReferralUnitByShortName(String shortName) {
		return referralUnitRepository.findReferralUnitByShortName(shortName);
	}

	@Override
	@Audit(action = "ReferralUnitServiceImpl.getAllReferralUnits()")
	public Iterable<ReferralUnit> getAllReferralUnits() {
		return referralUnitRepository.findAll();
	}

	@Override
	@Audit(action =  "ReferralUnitServiceImpl.addReferralUnit()")
	public void addReferralUnit(ReferralUnit referralUnit) {
		referralUnitRepository.save(referralUnit);
	}

	@Override
	@Audit(action = "ReferralUnitServiceImpl.updateReferralUnit()")
	public void updateReferralUnit(ReferralUnit referralUnit) {
		referralUnitRepository.save(referralUnit);
	}

	@Override
	@Audit(action = "ReferralUnitServiceImpl.deleteReferralunit()")
	public void deleteReferralunit(long id) {
		ReferralUnit referralUnit = findReferralUnitById(id);
		referralUnitRepository.delete(referralUnit);
	}

	@Override
	@Audit(action = "ReferralUnitServiceImpl.getValues()")
	public <T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor) {
		return listToExtractFrom.stream().map(extractor).collect(Collectors.toList());
	}

}
