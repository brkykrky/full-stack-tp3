package fr.fullstack.shopapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private float price;

	@OneToMany(cascade = {CascadeType.ALL})
	@Size(min=1)
	private List<LocalizedProduct> localizedProduct = new ArrayList<LocalizedProduct>();


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

    public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}

	public List<LocalizedProduct> getLocalizedProducts() {
		return localizedProduct;
	}
	
	public void setLocalizedProducts(List<LocalizedProduct> localizedProduct) {
		this.localizedProduct = localizedProduct;
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
