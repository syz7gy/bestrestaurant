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
import co.edu.unbosque.services.CatService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
@Transactional
public class CatController {

	@Autowired
	private CatService catServ;

	public CatController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(path = "/createcatjson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewUserWithJson(@RequestBody Dessert newCat) {
		int status = catServ.create(newCat);

		if (status == 0) {
			return new ResponseEntity<String>("Cat created succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error creating the cat",
					HttpStatus.NOT_ACCEPTABLE);

		}

	}

	@GetMapping(path = "getallcats")
	public ResponseEntity<List<Dessert>> getAll() {
		List<Dessert> cats = catServ.getAll();

		if (cats.isEmpty()) {
			return new ResponseEntity<List<Dessert>>(cats, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Dessert>>(cats, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping(path = "/deletecatsbyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		int status = catServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<String>("Cat eliminated succesfully", HttpStatus.ACCEPTED);

		} else {
			return new ResponseEntity<String>("Error deleting the cat", HttpStatus.NOT_FOUND);
		}

	}

}
