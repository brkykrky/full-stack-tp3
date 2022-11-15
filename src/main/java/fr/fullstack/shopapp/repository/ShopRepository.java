package fr.fullstack.shopapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fullstack.shopapp.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    // FILTERS
    List<Shop> findByInVacations(boolean inVacations, Pageable pageable);

    List<Shop> findByCreatedAtLessThan(LocalDate date, Pageable pageable);

    List<Shop> findByCreatedAtGreaterThan(LocalDate date, Pageable pageable);

    List<Shop> findByCreatedAtBetween(LocalDate dateStart, LocalDate dateEnd, Pageable pageable);

    List<Shop> findByInVacationsAndCreatedAtLessThan(boolean inVacations, LocalDate date, Pageable pageable);

    List<Shop> findByInVacationsAndCreatedAtGreaterThan(boolean inVacations, LocalDate date, Pageable pageable);

    List<Shop> findByInVacationsAndCreatedAtLessThanAndCreatedAtGreaterThan(boolean inVacations, LocalDate dateStart,
            LocalDate dateEnd, Pageable pageable);

    // SORT
    List<Shop> findByOrderByNameAsc(Pageable pageable);

    List<Shop> findByOrderByCreatedAtAsc(Pageable pageable);

    // @Query()
    // List<Shop> findByOrderByNbProductsAsc(Pageable pageable);
}