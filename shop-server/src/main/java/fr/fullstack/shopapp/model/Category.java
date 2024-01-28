package fr.fullstack.shopapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    @NotNull(message = "Name may not be null")
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Product> products = new ArrayList<Product>();
}
