package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.User;
import co.edu.unbosque.services.UserService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
@Transactional
public class UserController {

	@Autowired
	private UserService userServ;

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping(path = "/saludar")
	public ResponseEntity<String> saludar() {

		return new ResponseEntity<String>("Hello World", HttpStatus.OK);

	}

	@PostMapping(path = "/createuserjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewUserWithJson(@RequestBody User newUser) {
		int status = userServ.create(newUser);

		if (status == 0) {
			return new ResponseEntity<String>("User created succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error creating the user maybe the username is already taken",
					HttpStatus.NOT_ACCEPTABLE);

		}

	}

	@PostMapping(path = "/updateuserjson/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateUserWithJson(@RequestBody User newUser, @PathVariable Long id) {
		int status = userServ.updateById(id, newUser);

		if (status == 0) {
			return new ResponseEntity<String>("User updated succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error updating the user maybe the username is already taken",
					HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping(path = "getall")
	public ResponseEntity<List<User>> getAll() {
		List<User> users = userServ.getAll();

		if (users.isEmpty()) {
			return new ResponseEntity<List<User>>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<User>>(users, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping(path = "/deletebyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		int status = userServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<String>("User eliminated succesfully", HttpStatus.ACCEPTED);

		} else {
			return new ResponseEntity<String>("Error deleting the user", HttpStatus.NOT_FOUND);
		}

	}

}
