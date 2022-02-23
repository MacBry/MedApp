package pl.mac.bry.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class ReferralUnit implements Serializable {
	 
	private static final long serialVersionUID = -1894555121539128046L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Referral_unit_ID")
	private long id;
	
	private String fullName;
	
	private String shortName;
	
	private Address address =new Address();
	
	@OneToOne
	private ReferralUnitDetails referralUnitDetails;
	
	public void addReferralUnitDetail(ReferralUnitDetails referralUnitDetails) {
		this.referralUnitDetails = referralUnitDetails;
	}
}
