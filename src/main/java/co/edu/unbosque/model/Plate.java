package co.edu.unbosque.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plate")
public class Plate {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(unique = true)
	private String plateName;
	private String price;
	private boolean isVegeterian;
	private ArrayList<String> ingredients;
	private LocalDateTime dateTime;
	
	public Plate() {
		
	}

	public Plate(Long id, String plateName, String price, boolean isVegeterian, ArrayList<String> ingredients,
			LocalDateTime dateTime) {
		super();
		this.id = id;
		this.plateName = plateName;
		this.price = price;
		this.isVegeterian = isVegeterian;
		this.ingredients = ingredients;
		this.dateTime = dateTime;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getPlateName() {
		return plateName;
	}

	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public boolean isVegeterian() {
		return isVegeterian;
	}

	public void setVegeterian(boolean isVegeterian) {
		this.isVegeterian = isVegeterian;
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, plateName, price, isVegeterian, ingredients, dateTime);
	}

	@Override
	public String toString() {
		return "Plate [id=" + id + ", plateName=" + plateName + ", price=" + price + ", isVegeterian=" + isVegeterian
				+ ", ingredients=" + ingredients + ", dateTime=" + dateTime + "]";
	}
	
}
