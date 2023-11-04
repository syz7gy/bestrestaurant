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
import co.edu.unbosque.services.PlateService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/Plate")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
@Transactional
public class PlateController {
	
	@Autowired
	private PlateService plateServ;
	
	public PlateController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(path = "/createplatejson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewUserWithJson(@RequestBody Plate newPlate) {
		int status = plateServ.create(newPlate);

		if (status == 0) {
			return new ResponseEntity<String>("plate created succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error creating the plate",
					HttpStatus.NOT_ACCEPTABLE);

		}

	}

	@GetMapping(path = "getallplates")
	public ResponseEntity<List<Plate>> getAll() {
		List<Plate> plates = plateServ.getAll();

		if (plates.isEmpty()) {
			return new ResponseEntity<List<Plate>>(plates, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Plate>>(plates, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping(path = "/deleteplatesbyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		int status = plateServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<String>("plate eliminated succesfully", HttpStatus.ACCEPTED);

		} else {
			return new ResponseEntity<String>("Error deleting the plate", HttpStatus.NOT_FOUND);
		}

	}

}
