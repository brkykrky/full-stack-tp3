package fr.fullstack.shopapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalTime;

@Entity
@Table(name = "openingHours")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpeningHoursShop {
    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull(message = "CloseAt may not be null")
    private LocalTime closeAt;

    @Column(nullable = false)
    @NotNull(message = "Day may not be null")
    @Min(value = 1, message = "Day should not be less than 1")
    @Max(value = 7, message = "Day should not be greater than 7")
    private int day;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull(message = "OpenAt may not be null")
    private LocalTime openAt;
}
