package fr.fullstack.shopapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float price;

	// @OneToMany(cascade = {CascadeType.ALL})
	// private List<Translation> translations = new ArrayList<Translation>();

    @Column
    private String description;

	@ManyToOne  
	private Shop shop;

	@ManyToMany
	@JoinTable(
		name = "products_categories", 
		joinColumns = @JoinColumn(name = "product_id"), 
		inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories = new ArrayList<Category>();

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

    public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}

	// public List<Translation> getTranslations() {
	// 	return translations;
	// }
	
	// public void setTranslations(List<Translation> translations) {
	// 	this.translations = translations;
	// }

    public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Shop getShop() {
		return shop;
	}
	
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Category> getCategories() {
		return categories;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
