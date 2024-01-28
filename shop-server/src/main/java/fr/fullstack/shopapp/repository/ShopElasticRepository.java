package fr.fullstack.shopapp.repository;

import fr.fullstack.shopapp.model.Shop;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ShopElasticRepository extends Repository<Shop,Long> {
    List<Shop> findAllByName(String name);
}