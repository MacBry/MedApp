package pl.mac.bry.entities.dto;

import lombok.Data;
import pl.mac.bry.entities.enums.Deadline;
import pl.mac.bry.entities.enums.StreetPrefix;

@Data
public class ReferralUnitDTO {
	
	private long id;
	private String fullName;
	private String shortName;
	private String referralCountry;
	private String referralCity;
	private StreetPrefix referralStreetPrefix;
	private String referralStreet;
	private String referralZipCode;
	private String referralBuildingNumber;
	private String referralApartmentNumber;
	private long referralNipNumber;
	private long referralRegonNumber;
	private String referralPhoneNumber;
	private String referralEmail;
	private String referralResortBookNumber;
	private Deadline referralDeadLine;
	
}
