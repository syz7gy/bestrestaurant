package co.edu.unbosque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Plate;
import co.edu.unbosque.repository.PlateRepository;

@Service
public class PlateService implements CRUDOperations<Plate> {
	
	@Autowired
	private PlateRepository plateRepo;

	public PlateService() {

	}

	public PlateService(PlateRepository plateRepo) {
		super();
		this.plateRepo = plateRepo;
	}

	public boolean findNameAlreadyTaken(Plate newPlate) {

		Optional<Plate> found = plateRepo.findByName(newPlate.getPlateName());

		return found.isPresent() ? true : false;
	}

	@Override
	public int create(Plate data) {
		if (findNameAlreadyTaken(data)) {
			return 1;
		} else {
			plateRepo.save(data);
			return 0;
		}
	}

	@Override
	public List<Plate> getAll() {
		return plateRepo.findAll();
	}

	@Override
	public int deleteById(Long id) {
		Optional<Plate> found = plateRepo.findById(id);

		if (found.isPresent()) {
			plateRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}

	}

	@Override
	public int updateById(Long id, Plate newData) {

		Optional<Plate> found = plateRepo.findById(id);
		Optional<Plate> newFound = plateRepo.findByName(newData.getPlateName());

		if (found.isPresent() && !newFound.isPresent()) {

			Plate temp = found.get();
			temp.setPlateName(newData.getPlateName());
			temp.setPrice(newData.getPrice());
			temp.setVegeterian(newData.isVegeterian());
			temp.setIngredients(newData.getIngredients());
			temp.setDateTime(newData.getDateTime());
			plateRepo.save(temp);
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
		return plateRepo.count();
	}

	@Override
	public boolean exists(Long id) {
		return plateRepo.existsById(id) ? true : false;
	}

}
