package fr.fullstack.shopapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.fullstack.shopapp.entity.Shop;
import fr.fullstack.shopapp.repository.ShopRepository;

@Service
public class ShopService {
	@Autowired
    private ShopRepository shopRepository;
	
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
            List<Shop> shopList;
            switch (sortBy.get()) {
                case "name" :
                    shopList = shopRepository.findByOrderByNameAsc(pageable);
                    break;
                default :
                    shopList = shopRepository.findByOrderByCreatedAtAsc(pageable);
                    break;
                }
            return new PageImpl<Shop>(shopList);
        }

        // FILTERS
        List<Shop> shopList = getShopListWithFilter(inVacations, createdBefore, createdAfter, pageable);
        if (shopList != null) {
            return new PageImpl<Shop>(shopList);
        }

        // NONE
        return shopRepository.findAll(pageable);
    }
	
	public Shop createShop(Shop shop) throws Exception  {
        try {
            return shopRepository.save(shop);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
	
	public Shop updateShop(Shop shop) throws Exception {
        Optional<Shop> shopFound = shopRepository.findById(shop.getId());

        if (shopFound.isPresent()) {
            Shop shopUpdate = shopFound.get();
            shopUpdate.setInVacations(shop.getInVacations());
            shopUpdate.setName(shop.getName());
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

    private List<Shop> getShopListWithFilter(
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
