package pl.mac.bry.entities.dto;

import lombok.Data;
import pl.mac.bry.entities.enums.ABOBloodGroup;
import pl.mac.bry.entities.enums.RhDFactor;

@Data
public class PatientDTO {
	
	private long id;
	private String firstName;
	private String lastName;
	private String pesel;
	private ABOBloodGroup aboBloodGroup;
	private RhDFactor rhDFactor;
	private String patientPhoneNumber;
	private String patientEmail;

}
