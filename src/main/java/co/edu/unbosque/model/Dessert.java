package co.edu.unbosque.model;

import java.util.Objects;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DiscriminatorValue("cat")
public class Dessert extends Plate {
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	private int horasSueño;
	
	public Dessert() {
		
	}

	public Dessert( int horasSueño) {
		super();
		this.horasSueño = horasSueño;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getHorasSueño() {
		return horasSueño;
	}

	public void setHorasSueño(int horasSueño) {
		this.horasSueño = horasSueño;
	}

	@Override
	public int hashCode() {
		return Objects.hash(horasSueño, id);
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
		return horasSueño == other.horasSueño && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Cat [id=" + id + ", horasSueño=" + horasSueño + "]";
	}
	
	

}
