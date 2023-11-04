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
import co.edu.unbosque.services.MainCourseService;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/MainCourse	")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
@Transactional
public class MainCourseController {

	@Autowired
	private MainCourseService mainCoServ;

	public MainCourseController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping(path = "/createmaincoursejson", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createNewMainCourseWithJson(@RequestBody MainCourse newMainCo) {
		int status = mainCoServ.create(newMainCo);

		if (status == 0) {
			return new ResponseEntity<String>("Main course created succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error creating the main course",
					HttpStatus.NOT_ACCEPTABLE);

		}

	}
	
	@PostMapping(path = "/updatemaincoursejson/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateMainCourseWithJson(@RequestBody MainCourse newMainCo, @PathVariable Long id) {
		int status = mainCoServ.updateById(id, newMainCo);

		if (status == 0) {
			return new ResponseEntity<String>("Main course updated succesfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Error updating the main course maybe the main course is already taken",
					HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping(path = "/getallmaincourses")
	public ResponseEntity<List<MainCourse>> getAll() {
		List<MainCourse> mainCourses = mainCoServ.getAll();

		if (mainCourses.isEmpty()) {
			return new ResponseEntity<List<MainCourse>>(mainCourses, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<MainCourse>>(mainCourses, HttpStatus.ACCEPTED);
		}
	}

	@DeleteMapping(path = "/deletemaincoursesbyid/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
		int status = mainCoServ.deleteById(id);

		if (status == 0) {
			return new ResponseEntity<String>("maincourse eliminated succesfully", HttpStatus.ACCEPTED);

		} else {
			return new ResponseEntity<String>("Error deleting the maincourse", HttpStatus.NOT_FOUND);
		}

	}

}

