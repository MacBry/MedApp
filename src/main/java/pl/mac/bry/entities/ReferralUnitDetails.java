package pl.mac.bry.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ReferralUnitDetails implements Serializable {

	
	private static final long serialVersionUID = 9091348358024960344L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Referral_Unit_Detail_ID")
	private long id;
	
	private long nipNumber;
	
	private long regonNumber;
	
	private String phoneNumber;
	
	private String email;
	
	private String resortBookNumber;
}
