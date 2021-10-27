package pl.mac.bry.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.mac.bry.entities.User;
import pl.mac.bry.entities.UserRole;
import pl.mac.bry.entities.UserRoles;
import pl.mac.bry.repositories.UserRepository;
import pl.mac.bry.repositories.UserRoleRepository;
import pl.mac.bry.services.UserRegistrationService;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

	private UserRepository userRepository;
	private UserRoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public UserRegistrationServiceImpl(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	public void setRoleRepository(UserRoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public void addWithDefaoultRole(User user) {
		UserRole defaultRole = roleRepository.findByRole(UserRoles.DEFAULT_ROLE.getDescription());
		user.getRoles().add(defaultRole);
		String passwordHash = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHash);
		userRepository.save(user);
	}

}
