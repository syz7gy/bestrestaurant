package co.edu.unbosque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.MainCourse;
import co.edu.unbosque.repository.DogRepository;

@Service
public class DogService implements CRUDOperations<MainCourse>{

	@Autowired
	private DogRepository dogRepo;
	
	public DogService() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean findNameAlreadyTaken(MainCourse newDog) {

		Optional<MainCourse> found = dogRepo.findByNombre(newDog.getNombre());

		return found.isPresent() ? true : false;
	}

	@Override
	public int create(MainCourse data) {
		if (findNameAlreadyTaken(data)) {
			return 1;
		} else {
			dogRepo.save(data);
			return 0;
		}
	}

	@Override
	public List<MainCourse> getAll() {
		return dogRepo.findAll();
	}

	@Override
	public int deleteById(Long id) {
		Optional<MainCourse> found = dogRepo.findById(id);

		if (found.isPresent()) {
			dogRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}

	}

	@Override
	public int updateById(Long id, MainCourse newData) {

		Optional<MainCourse> found = dogRepo.findById(id);
		Optional<MainCourse> newFound = dogRepo.findByNombre(newData.getNombre());

		if (found.isPresent() && !newFound.isPresent()) {

			MainCourse temp = found.get();
			temp.setNombre(newData.getNombre());
			temp.setRaza(newData.getRaza());
			temp.setColorPelo(newData.getColorPelo());
			temp.setEdadAños(newData.getEdadAños());
			dogRepo.save(temp);
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
		return dogRepo.count();
	}

	@Override
	public boolean exists(Long id) {
		return dogRepo.existsById(id) ? true : false;
	}
	
	
}
