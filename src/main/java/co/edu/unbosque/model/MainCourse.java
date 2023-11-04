package co.edu.unbosque.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dessert")
@DiscriminatorValue("maincourse")
public class MainCourse extends Plate{

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

	public MainCourse() {

	}

	public MainCourse(Long id, String plateName, String price, boolean isVegeterian, ArrayList<String> ingredients,
			LocalDateTime dateTime) {
		super(id, plateName, price, isVegeterian, ingredients, dateTime);
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(id);
		return result;
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
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "MainCourse [id=" + id + "]";
	}

	

	

}
