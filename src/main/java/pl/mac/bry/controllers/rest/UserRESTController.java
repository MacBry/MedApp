package pl.mac.bry.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
	
	@GetMapping("/firstName/{firstName}")
	public Iterable<User> getUsersByFirstName(@PathVariable String firstName){
		return userService.FindUserByFirstName(firstName);
	}
	
	@GetMapping("/lastName/{lastName}")
	public Iterable<User> getUsersByLastName(@PathVariable String lastName){
		return userService.FindUserByLastName(lastName);
	}
	
	@GetMapping("/mail/{mail}")
	public User getUserByMail(@PathVariable String mail) {
		return userService.findUserByMail(mail);
	}
	
	@GetMapping("/id/{id}")
	public User getUserById(@PathVariable long id) {
		return userService.findUserById(id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveUser(@RequestBody User user){
		userService.addUser(user);
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@RequestBody User user) {
		userService.updateUser(user);
	}
}
