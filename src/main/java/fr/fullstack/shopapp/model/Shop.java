package fr.fullstack.shopapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shops")
@Indexed(index = "idx_shops")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @NotNull(message = "InVacations may not be null")
    @GenericField
    private boolean inVacations;

    @Column(nullable = false)
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    @NotNull(message = "Name may not be null")
    @FullTextField
    private String name;

    @Formula(value = "(SELECT COUNT(DISTINCT pc.category_id) FROM products_categories pc "
            + "WHERE pc.product_id IN (SELECT p.id FROM products p WHERE p.shop_id = id))")
    private Long nbCategories;

    @Formula(value = "(SELECT COUNT(*) FROM products p WHERE p.shop_id = id)")
    private Long nbProducts;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<@Valid OpeningHoursShop> openingHours = new ArrayList<OpeningHoursShop>();

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products = new ArrayList<Product>();
}
