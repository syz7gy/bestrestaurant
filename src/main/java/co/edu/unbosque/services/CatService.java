package co.edu.unbosque.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Dessert;
import co.edu.unbosque.repository.CatRepository;

@Service
public class CatService implements CRUDOperations<Dessert> {

	@Autowired
	private CatRepository catRepo;
	
	public CatService() {
		// TODO Auto-generated constructor stub
	}
	public boolean findNameAlreadyTaken(Dessert newCat) {

		Optional<Dessert> found = catRepo.findByNombre(newCat.getNombre());

		return found.isPresent() ? true : false;
	}

	@Override
	public int create(Dessert data) {
		if (findNameAlreadyTaken(data)) {
			return 1;
		} else {
			catRepo.save(data);
			return 0;
		}
	}

	@Override
	public List<Dessert> getAll() {
		return catRepo.findAll();
	}

	@Override
	public int deleteById(Long id) {
		Optional<Dessert> found = catRepo.findById(id);

		if (found.isPresent()) {
			catRepo.delete(found.get());
			return 0;
		} else {
			return 1;
		}

	}

	@Override
	public int updateById(Long id, Dessert newData) {

		Optional<Dessert> found = catRepo.findById(id);
		Optional<Dessert> newFound = catRepo.findByNombre(newData.getNombre());

		if (found.isPresent() && !newFound.isPresent()) {

			Dessert temp = found.get();
			temp.setNombre(newData.getNombre());
			temp.setRaza(newData.getRaza());
			temp.setColorPelo(newData.getColorPelo());
			temp.setHorasSueño(newData.getHorasSueño());
			catRepo.save(temp);
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
		return catRepo.count();
	}

	@Override
	public boolean exists(Long id) {
		return catRepo.existsById(id) ? true : false;
	}

	
}
