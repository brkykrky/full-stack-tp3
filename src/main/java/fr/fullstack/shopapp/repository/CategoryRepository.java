package fr.fullstack.shopapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fullstack.shopapp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Page<Category> findByOrderByIdAsc(Pageable pageable);
}