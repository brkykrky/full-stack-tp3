package fr.fullstack.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fullstack.shopapp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}