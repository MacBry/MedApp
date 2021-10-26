package pl.mac.bry.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Entity
@Data
public class User implements Serializable {

	private static final long serialVersionUID = -4286340265043531776L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_ID")
	private long id;

	@NotBlank(message = "First Name is mandatory")
	@Length(min = 2, max = 20)
	private String firstName;

	@NotBlank(message = "Last Name is mandatory")
	@Length(min = 2, max = 20)
	private String lastName;

	@NotBlank(message = "Email is mandatory")
	@Email
	private String email;

	@NotBlank(message = "Password is mandatory")
	@Length(min = 3, max = 20)
	private String password;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<UserRole> roles = new HashSet<>();

}
