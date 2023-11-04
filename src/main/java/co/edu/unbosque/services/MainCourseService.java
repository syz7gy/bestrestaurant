package co.edu.unbosque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Dessert;
import co.edu.unbosque.model.MainCourse;
import co.edu.unbosque.repository.MainCourseRepository;

@Service
public class MainCourseService implements CRUDOperations<MainCourse>{

	@Autowired
	private MainCourseRepository mainCoRepo;
	
	public MainCourseService() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean findNameAlreadyTaken(MainCourse newMainCo) {

		Optional<MainCourse> found = mainCoRepo.findByNombre(newMainCo.getPlateName());

		return found.isPresent() ? true : false;
	}

	@Override
	public int create(MainCourse data) {
		if (findNameAlreadyTaken(data)) {
			return 1;
		} else {
			mainCoRepo.save(data);
			return 0;
		}
	}

	@Override
	public List<MainCourse> getAll() {
		return mainCoRepo.findAll();
	}

	@Override
	public int deleteById(Long id) {
		Optional<MainCourse> found = mainCoRepo.findById(id);

		if (found.isPresent()) {
			mainCoRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}

	}

	@Override
	public int updateById(Long id, MainCourse newData) {

		Optional<MainCourse> found = mainCoRepo.findById(id);
		Optional<MainCourse> newFound = mainCoRepo.findByNombre(newData.getPlateName());

		if (found.isPresent() && !newFound.isPresent()) {

			MainCourse temp = found.get();
			temp.setPlateName(newData.getPlateName());
			temp.setPrice(newData.getPrice());
			temp.setVegeterian(newData.isVegeterian());
			temp.setIngredients(newData.getIngredients());
			temp.setDateTime(newData.getDateTime());
			mainCoRepo.save(temp);
			return 0;
		} else if (found.isPresent() && newFound.isPresent()) {
			return 1;
		} else if (!found.isPresent()) {
			return 2;
		} else {
			return 3;
		}

	}

	@Override
	public long count() {
		return mainCoRepo.count();
	}

	@Override
	public boolean exists(Long id) {
		return mainCoRepo.existsById(id) ? true : false;
	}
	
	
}
