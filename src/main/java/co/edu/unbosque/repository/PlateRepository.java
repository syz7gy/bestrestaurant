package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.model.Plate;

public interface PlateRepository extends JpaRepository<Plate, Long> {

	public Optional<Plate> findByName(String name);
	
	public void deleteByName(String name);

}
