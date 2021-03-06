package pl.mac.bry.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import pl.mac.bry.entities.enums.Deadline;

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
	
	private Deadline deadline;
	
	@OneToOne
	private ReferralUnit referralUnit;
	
	public void addReferralUnit(ReferralUnit referralUnit) {
		this.referralUnit = referralUnit;
	}
}
