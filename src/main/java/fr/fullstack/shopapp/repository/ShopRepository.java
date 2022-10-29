package fr.fullstack.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fullstack.shopapp.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {
	
}
