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

import co.edu.unbosque.model.MainCourse;
import co.edu.unbosque.services.DogService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
@Transactional
public class DogController {

	@Autowired
	private DogService dogServ;

	public DogController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(path = "/createdogjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewUserWithJson(@RequestBody MainCourse newDog) {
		int status = dogServ.create(newDog);

		if (status == 0) {
			return new ResponseEntity<String>("Dog created succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error creating the dog",
					HttpStatus.NOT_ACCEPTABLE);

		}

	}

	@GetMapping(path = "getalldogs")
	public ResponseEntity<List<MainCourse>> getAll() {
		List<MainCourse> dogs = dogServ.getAll();

		if (dogs.isEmpty()) {
			return new ResponseEntity<List<MainCourse>>(dogs, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<MainCourse>>(dogs, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping(path = "/deletedogsbyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		int status = dogServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<String>("Dog eliminated succesfully", HttpStatus.ACCEPTED);

		} else {
			return new ResponseEntity<String>("Error deleting the dog", HttpStatus.NOT_FOUND);
		}

	}

}

