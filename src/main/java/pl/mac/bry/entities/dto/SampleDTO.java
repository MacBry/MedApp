package pl.mac.bry.entities.dto;

import java.time.LocalDateTime;

import lombok.Data;
import pl.mac.bry.entities.enums.ABOBloodGroup;
import pl.mac.bry.entities.enums.RhDFactor;
import pl.mac.bry.entities.enums.SampleType;

@Data
public class SampleDTO {
	
	private long id;
	private LocalDateTime donationDateTime;
	private LocalDateTime rejestrationDateTime;
	private SampleType sampleType;
	private String patientFirstName;
	private String patientLastName;
	private String patientPesel;
	private ABOBloodGroup patientAboGroup;
	private RhDFactor patientRhDFactor;
	private String referralUnitNumber;
	
}
