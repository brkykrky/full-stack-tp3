package fr.fullstack.shopapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "shops")
public class Shop {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate createdAt;
	
	@Column(nullable = false)
	@Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
	@NotNull(message = "Name may not be null")
    private String name;
	
	@Column(nullable = false)
	@NotNull(message = "InVacations may not be null")
    private boolean inVacations;
	
	@OneToMany(cascade = {CascadeType.ALL})
	private List<@Valid OpeningHoursShop> openingHours = new ArrayList<OpeningHoursShop>();

	@Formula(value = "(SELECT COUNT(*) FROM products p WHERE p.shop_id = id)")
	private Long nbProducts;

	@Formula(value = "(SELECT COUNT(DISTINCT pc.category_id) FROM products_categories pc "
					+ "WHERE pc.product_id IN (SELECT p.id FROM products p WHERE p.shop_id = id))")
	private Long nbCategories;
	
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

	public long getNbProducts() {
		return nbProducts;
	}
	
	public void setNbProducts(long nbProducts) {
		this.nbProducts = nbProducts;
	}

	public long getNbCategories() {
		return nbCategories;
	}
	
	public void setNbCategories(long nbCategories) {
		this.nbCategories = nbCategories;
	}
}
