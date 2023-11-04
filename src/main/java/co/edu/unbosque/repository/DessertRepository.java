package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.model.Dessert;

public interface DessertRepository extends JpaRepository<Dessert, Long> {

	public Optional<Dessert> findByNombre(String name);

	public void deleteByNombre(String name);

}
