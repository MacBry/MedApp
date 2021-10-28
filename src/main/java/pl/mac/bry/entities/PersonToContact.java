package pl.mac.bry.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Data
public class PersonToContact implements Serializable {

	private static final long serialVersionUID = -4207062736508145751L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Person_to_contact_ID")
	private long id;
	
	@NotBlank(message = "First Name is mandatory")
	@Length(min = 2, max = 20)
	private String firstName;
	
	@NotBlank(message = "Last Name is mandatory")
	@Length(min =2, max = 20)
	private String lastName;
	
	@Email
	private String email;
	
	private String phoneNumber;
	
	@ManyToOne
	@JoinColumn(name = "patient_details_id")
	private PatientDetails patientDetails;

}
