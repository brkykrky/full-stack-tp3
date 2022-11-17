package fr.fullstack.shopapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    @Query(
        value="SELECT *,"
            + "(SELECT COUNT(*) FROM products p WHERE p.shop_id = s.id) as nbProducts, "
            + "(SELECT COUNT(DISTINCT pc.category_id) FROM products_categories pc WHERE pc.product_id IN (SELECT p.id FROM products p WHERE p.shop_id = s.id)) as nbCategories "
            + "FROM shops s "
            + "ORDER BY (SELECT COUNT(*) FROM products p WHERE p.shop_id = s.id) DESC",
        nativeQuery = true
    )
    List<Shop> findByOrderByNbProductsAsc(Pageable pageable);
}