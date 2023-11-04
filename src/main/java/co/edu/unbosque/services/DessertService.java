package co.edu.unbosque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Dessert;
import co.edu.unbosque.repository.DessertRepository;

@Service
public class DessertService implements CRUDOperations<Dessert> {

	@Autowired
	private DessertRepository dessRepo;
	
	public DessertService() {
		// TODO Auto-generated constructor stub
	}
	public boolean findNameAlreadyTaken(Dessert newData) {

		Optional<Dessert> found = dessRepo.findByNombre(newData.getPlateName());

		return found.isPresent() ? true : false;
	}

	@Override
	public int create(Dessert data) {
		if (findNameAlreadyTaken(data)) {
			return 1;
		} else {
			dessRepo.save(data);
			return 0;
		}
	}

	@Override
	public List<Dessert> getAll() {
		return dessRepo.findAll();
	}

	@Override
	public int deleteById(Long id) {
		Optional<Dessert> found = dessRepo.findById(id);

		if (found.isPresent()) {
			dessRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}

	}

	@Override
	public int updateById(Long id, Dessert newData) {

		Optional<Dessert> found = dessRepo.findById(id);
		Optional<Dessert> newFound = dessRepo.findByNombre(newData.getPlateName());

		if (found.isPresent() && !newFound.isPresent()) {

			Dessert temp = found.get();
			temp.setPlateName(newData.getPlateName());
			temp.setPrice(newData.getPrice());
			temp.setVegeterian(newData.isVegeterian());
			temp.setIngredients(newData.getIngredients());
			temp.setDateTime(newData.getDateTime());
			temp.setBittersweet(newData.isBittersweet());
			dessRepo.save(temp);
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
		return dessRepo.count();
	}

	@Override
	public boolean exists(Long id) {
		return dessRepo.existsById(id) ? true : false;
	}

	
}
