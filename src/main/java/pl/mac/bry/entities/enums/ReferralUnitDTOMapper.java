package pl.mac.bry.entities.enums;

import org.springframework.stereotype.Service;

import pl.mac.bry.entities.ReferralUnit;
import pl.mac.bry.entities.dto.ReferralUnitDTO;

@Service
public class ReferralUnitDTOMapper {

	public ReferralUnitDTO map (ReferralUnit referralUnit) {
		ReferralUnitDTO dto = new ReferralUnitDTO();
		dto.setId(referralUnit.getId());
		dto.setFullName(referralUnit.getFullName());
		dto.setShortName(referralUnit.getShortName());
		dto.setReferralCountry(referralUnit.getAddress().getCountry());
		dto.setReferralCity(referralUnit.getAddress().getCity());
		dto.setReferralStreetPrefix(referralUnit.getAddress().getStreetPrefix());
		dto.setReferralStreet(referralUnit.getAddress().getStreet());
		dto.setReferralZipCode(referralUnit.getAddress().getZipCode());
		dto.setReferralBuildingNumber(referralUnit.getAddress().getBuildingNumber());
		dto.setReferralApartmentNumber(referralUnit.getAddress().getApartmentNumber());
		dto.setReferralNipNumber(referralUnit.getReferralUnitDetails().getNipNumber());
		dto.setReferralRegonNumber(referralUnit.getReferralUnitDetails().getRegonNumber());
		dto.setReferralPhoneNumber(referralUnit.getReferralUnitDetails().getPhoneNumber());
		dto.setReferralEmail(referralUnit.getReferralUnitDetails().getEmail());
		dto.setReferralResortBookNumber(referralUnit.getReferralUnitDetails().getResortBookNumber());
		dto.setReferralDeadLine(referralUnit.getReferralUnitDetails().getDeadline());
		return dto;
	}
}
