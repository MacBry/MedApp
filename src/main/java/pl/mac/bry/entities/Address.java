package pl.mac.bry.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import pl.mac.bry.entities.enums.StreetPrefix;

@Entity
@Data
public class Address implements Serializable {

	private static final long serialVersionUID = 6353868846352896862L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Address_ID")
	private long id;
		
	private String country;
	
	private String city;
	
	private StreetPrefix streetPrefix;
		
	private String street;

	private String zipCode;
	
	private String buildingNumber;
	
	private String apartmentNumber ;
	
	@ManyToOne
	@JoinColumn(name = "patient_details_id")
	private PatientDetails patientDetails;

}
