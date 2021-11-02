package pl.mac.bry.services;

import java.util.List;
import java.util.function.Function;

import pl.mac.bry.entities.User;

public interface UserService {
	
	User findUserByMail(String mail);
	
	User findUserById(long id);
	
	Iterable<User> FindUserByFirstName(String firstName);
	
	Iterable<User> FindUserByLastName(String lastName);
	
	Iterable<User> FindUserByFirstNameAndLastName(String firstName, String lastName);
	
	Iterable<User> getAllUsers();
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(long id);

	<T, O> List<T> getValues(Class<T> clazz, List<O> listToExtractFrom, Function<O, T> extractor);
	
}
