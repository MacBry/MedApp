package pl.mac.bry.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Sample implements Serializable {

	private static final long serialVersionUID = 6407381916167426935L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sample_ID")
	private long id;
	
	private LocalDateTime colectionDateTime;
	
	private SampleType sampleType;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

}
