package pl.mac.bry.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.mac.bry.entities.User;
import pl.mac.bry.repositories.UserRepository;
import pl.mac.bry.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User findUserByMail(String mail) {
		return  userRepository.findByEmail(mail)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user mail:" + mail));
	}

	@Override
	public User findUserById(long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
	}

	@Override
	public Iterable<User> FindUserByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	@Override
	public Iterable<User> FindUserByLastName(String lastName) {
		return userRepository.findByLastName(lastName);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void addUser(User user) {
		hashPassword(user);
		userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		hashPassword(user);
		userRepository.save(user); 
	}

	@Override
	public void deleteUser(long id) {
		User user = findUserById(id);
		userRepository.delete(user);
	}
	
	private void hashPassword(User user) {
		String passwordHash = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHash);
	}

}
