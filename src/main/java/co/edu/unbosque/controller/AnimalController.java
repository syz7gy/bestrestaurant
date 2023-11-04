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

import co.edu.unbosque.model.Plate;
import co.edu.unbosque.services.AnimalService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
@Transactional
public class AnimalController {

	@Autowired
	private AnimalService animalServ;

	public AnimalController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(path = "/createanimaljson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewUserWithJson(@RequestBody Plate newAnimal) {
		int status = animalServ.create(newAnimal);

		if (status == 0) {
			return new ResponseEntity<String>("Animal created succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error creating the animal",
					HttpStatus.NOT_ACCEPTABLE);

		}

	}

	@GetMapping(path = "getallanimals")
	public ResponseEntity<List<Plate>> getAll() {
		List<Plate> animals = animalServ.getAll();

		if (animals.isEmpty()) {
			return new ResponseEntity<List<Plate>>(animals, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Plate>>(animals, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping(path = "/deleteanimalsbyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		int status = animalServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<String>("Animal eliminated succesfully", HttpStatus.ACCEPTED);

		} else {
			return new ResponseEntity<String>("Error deleting the animal", HttpStatus.NOT_FOUND);
		}

	}

}
