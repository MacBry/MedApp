package pl.mac.bry.controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.mac.bry.entities.User;
import pl.mac.bry.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRESTController {
	
	private UserService userService;

	@Autowired
	public UserRESTController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/")
	public Iterable<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/id/{id}")
	public User getUserById(@PathVariable long id) {
		return userService.findUserById(id);
	}
	
	@GetMapping("/id/{id}/first-name")
	public String getUserFirstName(@PathVariable long id) {
		return userService.findUserById(id).getFirstName();
	}
	
	@GetMapping("/first-names")
	public List<String> getAllusersFirstNames() {
		List<User> allUsers = (List<User>) userService.getAllUsers();
		return userService.getValues(String.class, allUsers, User::getFirstName);
	}
	
	@GetMapping("/id/{id}/last-name")
	public String getUserLastName(@PathVariable long id) {
		return userService.findUserById(id).getLastName();
	}
	
	@GetMapping("/last-names")
	public List<String> getAllUsersLastNames(){
		List<User> allUsers = (List<User>) userService.getAllUsers();
		return userService.getValues(String.class, allUsers, User::getLastName);
	}
	
	@GetMapping("/id/{id}/email")
	public String getUserEmail(@PathVariable long id) {
		return userService.findUserById(id).getEmail();
	}
	
	@GetMapping("/emails")
	public List<String> getAllUsersEmails(){
		List<User> allUsers = (List<User>) userService.getAllUsers();
		return userService.getValues(String.class, allUsers, User::getEmail);
	}
	
	@GetMapping("/id/{id}/password")
	public String getUserPassword(@PathVariable long id) {
		return userService.findUserById(id).getPassword();
	}
		
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody User user){
		userService.addUser(user);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user);
	}
	
	@DeleteMapping("/delete/id/{id}")
	public void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}
}
