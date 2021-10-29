package pl.mac.bry.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pl.mac.bry.entities.User;

public interface UserRepository extends CrudRepository<User,Long>{

	Optional<User> findByEmail(String username);
	
	Iterable<User> findByFirstName(String firstName);
	
	Iterable<User> findByLastName(String lastName);
	
	Iterable<User> findByFirstNameAndLastName(String firstName, String lastName);
}
