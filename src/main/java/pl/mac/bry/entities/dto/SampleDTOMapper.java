package pl.mac.bry.entities.dto;

import org.springframework.stereotype.Service;

import pl.mac.bry.entities.Sample;

@Service
public class SampleDTOMapper {
	
	public SampleDTO map (Sample sample) {
		SampleDTO dto =  new SampleDTO();
		dto.setId(sample.getId());
		dto.setDonationDateTime(sample.getDonationDateTime());
		dto.setRejestrationDateTime(sample.getRejestrationDateTime());
		dto.setSampleType(sample.getSampleType());
		dto.setPatientFirstName(sample.getPatient().getFirstName());
		dto.setPatientLastName(sample.getPatient().getLastName());
		dto.setPatientPesel(sample.getPatient().getPesel());
		dto.setPatientAboGroup(sample.getPatient().getAboGroup());
		dto.setPatientRhDFactor(sample.getPatient().getRhdFactor());
		dto.setReferralUnitNumber(convert(sample.getReferralUnit().getId()));
		return dto;
	}
	
	//Convert Referral Unit id to String pattern (88-refunitId)
	private String convert(Long refUnitId) {
		if(refUnitId % 10 < 1) {
			return "88-00" + refUnitId;
		}else if(refUnitId % 100 <1) {
			return "88-0" + refUnitId;
		}else return "88-" + refUnitId;
	}
	
}
