package fr.fullstack.shopapp.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "shops")
public class Shop {
	@Id
    @GeneratedValue
    private long id;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;
	
	@Column(nullable = false, unique = true)
    private String name;
	
	@Column(nullable = false)
    private boolean inVacations;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<OpeningHoursShop> openingHours = new ArrayList<OpeningHoursShop>();
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
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

	public List<OpeningHoursShop> getOpeningHours() {
		return openingHours;
	}
	
	public void setOpeningHours(List<OpeningHoursShop> openingHours) {
		this.openingHours = openingHours;
	}
}
