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
@Table(name = "maincourses")
@DiscriminatorValue("dessert")
public class Dessert extends Plate {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private boolean isBittersweet;
	
	public Dessert() {
		
	}

	public Dessert(Long id, boolean isBittersweet) {
		super();
		this.id = id;
		this.isBittersweet = isBittersweet;
	}

	public Dessert(Long id, String plateName, String price, boolean isVegeterian, ArrayList<String> ingredients,
			LocalDateTime dateTime) {
		super(id, plateName, price, isVegeterian, ingredients, dateTime);
		// TODO Auto-generated constructor stub
	}

	public Dessert(Long id, String plateName, String price, boolean isVegeterian, ArrayList<String> ingredients,
			LocalDateTime dateTime, Long id2, boolean isBittersweet) {
		super(id, plateName, price, isVegeterian, ingredients, dateTime);
		id = id2;
		this.isBittersweet = isBittersweet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isBittersweet() {
		return isBittersweet;
	}

	public void setBittersweet(boolean isBittersweet) {
		this.isBittersweet = isBittersweet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isBittersweet, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dessert other = (Dessert) obj;
		return isBittersweet == other.isBittersweet && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Dessert [id=" + id + ", isBittersweet=" + isBittersweet + "]";
	}

	
	
	

}
