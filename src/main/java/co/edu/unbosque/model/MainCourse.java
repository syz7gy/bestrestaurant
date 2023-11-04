package co.edu.unbosque.model;

import java.util.Objects;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("dog")
public class MainCourse extends Plate{

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private int edadAños;

	public MainCourse() {

	}

	public MainCourse( int edadAños) {
		super();
		this.edadAños = edadAños;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getEdadAños() {
		return edadAños;
	}

	public void setEdadAños(int edadAños) {
		this.edadAños = edadAños;
	}

	@Override
	public int hashCode() {
		return Objects.hash(edadAños, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MainCourse other = (MainCourse) obj;
		return edadAños == other.edadAños && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Dog [id=" + id + ", edadAños=" + edadAños + "]";
	}

}
