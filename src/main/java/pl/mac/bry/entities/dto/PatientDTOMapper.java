package pl.mac.bry.entities.dto;

import org.springframework.stereotype.Service;

import pl.mac.bry.entities.Patient;

@Service
public class PatientDTOMapper {

	public PatientDTO map (Patient patient) {
		PatientDTO dto = new PatientDTO();
		dto.setId(patient.getId());
		dto.setFirstName(patient.getFirstName());
		dto.setLastName(patient.getLastName());
		dto.setPesel(patient.getPesel());
		dto.setAboBloodGroup(patient.getAboGroup());
		dto.setRhDFactor(patient.getRhdFactor());
		dto.setPatientPhoneNumber(patient.getPatientDetails().getPhoneNumber());
		dto.setPatientEmail(patient.getPatientDetails().getEmail());
		return dto;
	}
}
