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
import javax.validation.constraints.Email;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

@Entity
@Data
public class PatientDetails implements Serializable {

	private static final long serialVersionUID = -5452508556293837919L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Patient_Detail_ID")
	private long id;
	
	@OneToMany(mappedBy = "patientDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)// to remove "cannot simultaneously fetch multiple bags"
	private List<PersonToContact> personsToContact = new ArrayList<PersonToContact>();

	@OneToMany(mappedBy = "patientDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)// to remove "cannot simultaneously fetch multiple bags"
	private List<Address> patientAdresses = new ArrayList<Address>();
	
	private String phoneNumber;
	
	@Email
	private String email;
	
	

}
