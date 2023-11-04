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

import co.edu.unbosque.model.Dessert;
import co.edu.unbosque.services.DessertService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/Dessert")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
@Transactional
public class DessertController {

	@Autowired
	private DessertService dessServ;

	public DessertController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(path = "/createdessertjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewDessertWithJson(@RequestBody Dessert newDessert) {
		int status = dessServ.create(newDessert);

		if (status == 0) {
			return new ResponseEntity<String>("Dessert created succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error creating the dessert",
					HttpStatus.NOT_ACCEPTABLE);

		}

	}

	@GetMapping(path = "getalldesserts")
	public ResponseEntity<List<Dessert>> getAll() {
		List<Dessert> desserts = dessServ.getAll();

		if (desserts.isEmpty()) {
			return new ResponseEntity<List<Dessert>>(desserts, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Dessert>>(desserts, HttpStatus.ACCEPTED);
		}
	}
	
	@PostMapping(path = "/updatedessertjson/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateDessertWithJson(@RequestBody Dessert newDessert, @PathVariable Long id) {
		int status = dessServ.updateById(id, newDessert);

		if (status == 0) {
			return new ResponseEntity<String>("Dessert updated succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error updating the dessert maybe the dessertname is already taken",
					HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@DeleteMapping(path = "/deletedessertsbyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		int status = dessServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<String>("Cat eliminated succesfully", HttpStatus.ACCEPTED);

		} else {
			return new ResponseEntity<String>("Error deleting the cat", HttpStatus.NOT_FOUND);
		}

	}

}
