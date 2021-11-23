package pl.mac.bry.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Data
public class Patient implements Serializable {

	private static final long serialVersionUID = -8686307114013800108L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Patient_ID")
	private long id;
	
	@NotBlank(message = "First Name is mandatory")
	@Length(min = 2, max = 20)
	private String firstName;
	
	@NotBlank(message = "Last Name is mandatory")
	@Length(min =2, max = 20)
	private String lastName;
	
	private String pesel;
	
	@OneToOne
	private PatientDetails  patientDetails;
	
	@OneToMany(mappedBy = "patient", cascade =  { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)// to remove "cannot simultaneously fetch multiple bags"
	private List<Sample> patientSamples = new ArrayList<Sample>();
	
	public void addPatientSample(Sample sample) {
		this.patientSamples.add(sample);
	}

}
