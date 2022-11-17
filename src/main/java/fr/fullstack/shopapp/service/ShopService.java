package fr.fullstack.shopapp.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.fullstack.shopapp.entity.Shop;
import fr.fullstack.shopapp.repository.ShopRepository;

@Service
public class ShopService {
	@Autowired
    private ShopRepository shopRepository;

    @PersistenceContext
    private EntityManager em;
	
	public Shop getShopById(long id) {
        return shopRepository.findById(id).orElse(null);
    }

    public Page<Shop> getShopList(
        Optional<String> sortBy,
        Optional<Boolean> inVacations,
        Optional<String> createdBefore,
		Optional<String> createdAfter,
        Pageable pageable
    ) {       
        // SORT
        if (sortBy.isPresent()) {
            switch (sortBy.get()) {
                case "name" :
                    return shopRepository.findByOrderByNameAsc(pageable);
                case "createdAt" :
                    return shopRepository.findByOrderByCreatedAtAsc(pageable);
                default :
                    return shopRepository.findByOrderByNbProductsAsc(pageable);
                }
        }

        // FILTERS
        Page<Shop> shopList = getShopListWithFilter(inVacations, createdBefore, createdAfter, pageable);
        if (shopList != null) {
            return shopList;
        }

        // NONE
        return shopRepository.findAll(pageable);
    }
	
    @Transactional
	public Shop createShop(Shop shop) throws Exception  {
        try {
            Shop newShop = shopRepository.save(shop);
            // Refresh the entity after the save. Otherwise, @Formula does not work.
            em.flush();
            em.refresh(newShop);
            return newShop;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
	
    @Transactional
	public Shop updateShop(Shop shop) throws Exception {
        Optional<Shop> shopFound = shopRepository.findById(shop.getId());
        if (shopFound.isPresent()) {
            return this.createShop(shop);
        } else {
            throw new Exception("Id not found");
        }
    }
	
	public void deleteShopById(long id) throws Exception {
        try {
            shopRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private Page<Shop> getShopListWithFilter(
        Optional<Boolean> inVacations,
        Optional<String> createdAfter,
		Optional<String> createdBefore,
        Pageable pageable
    ) {
        if (inVacations.isPresent() && createdBefore.isPresent() && createdAfter.isPresent()) {
            return shopRepository.findByInVacationsAndCreatedAtLessThanAndCreatedAtGreaterThan(
                inVacations.get(), LocalDate.parse(createdAfter.get()), LocalDate.parse(createdBefore.get()), pageable
            );
        }

        if (inVacations.isPresent() && createdBefore.isPresent()) {
            return shopRepository.findByInVacationsAndCreatedAtLessThan(
                inVacations.get(), LocalDate.parse(createdBefore.get()), pageable
            );
        }

        if (inVacations.isPresent() && createdAfter.isPresent()) {
            return shopRepository.findByInVacationsAndCreatedAtGreaterThan(
                inVacations.get(), LocalDate.parse(createdAfter.get()), pageable
            );
        }

        if (inVacations.isPresent()) {
            return shopRepository.findByInVacations(inVacations.get(), pageable);
        }

        if (createdBefore.isPresent() && createdAfter.isPresent()) {
            return shopRepository.findByCreatedAtBetween(
                LocalDate.parse(createdAfter.get()), LocalDate.parse(createdBefore.get()), pageable
            );
        }

        if (createdBefore.isPresent()) {
            return shopRepository.findByCreatedAtLessThan(
                LocalDate.parse(createdBefore.get()), pageable
            );
        }

        if (createdAfter.isPresent()) {
            return shopRepository.findByCreatedAtGreaterThan(
                LocalDate.parse(createdAfter.get()), pageable
            );
        }
        
        return null;
    }
}
