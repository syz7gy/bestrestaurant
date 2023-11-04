package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.model.MainCourse;

public interface MainCourseRepository extends JpaRepository<MainCourse, Long> {

	public Optional<MainCourse> findByNombre(String name);

	public void deleteByNombre(String name);

}
