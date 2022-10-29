package fr.fullstack.shopapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "shops")
public class Shop {
	@Id
    @GeneratedValue
    private long id;
	
	@Column(nullable = false, unique = true)
    private String name;
	
	@Column(nullable = false)
    private boolean inVacations;
	
//	@OneToMany
//	private List<OpeningHoursShop> employes = new ArrayList<OpeningHoursShop>();
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getInVacations() {
		return inVacations;
	}
	
	public void setInVacations(boolean inVacations) {
		this.inVacations = inVacations;
	}
}
