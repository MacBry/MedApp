package pl.mac.bry.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import pl.mac.bry.entities.enums.SampleType;

@Entity
@Data
public class Sample implements Serializable {

	private static final long serialVersionUID = 6407381916167426935L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sample_ID")
	private long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime donationDateTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime rejestrationDateTime;
	
	private SampleType sampleType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@OneToOne
	private ReferralUnit referralUnit;

}
