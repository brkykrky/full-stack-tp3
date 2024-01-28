package fr.fullstack.shopapp.model;

import fr.fullstack.shopapp.validation.StringEnumeration;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<Category>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @Size(min = 1, message = "At least one name and one description must be provided")
    private List<@Valid LocalizedProduct> localizedProduct = new ArrayList<LocalizedProduct>();

    @Column(nullable = false)
    @PositiveOrZero(message = "Price must be positive")
    @NotNull(message = "Price may not be null")
    private float price;

    @Column(nullable = true)
    @StringEnumeration(enumClass = Currency.class, message = "Currency must be one of the values : USD | BTC | EUR | JPY")
    private String currency;

    @ManyToOne
    private Shop shop;
    @PostLoad
    private void setDefaultCurrency() {
        if(currency == null || currency.isEmpty()){
            currency = Currency.EUR.name();
        }
    }
}
