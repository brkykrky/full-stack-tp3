package fr.fullstack.shopapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Shop> getShopList() {
        return shopRepository.findAll();
    }
	
	public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }
	
	public Shop updateShop(Shop shop) {
        Optional<Shop> shopFound = shopRepository.findById(shop.getId());

        if (shopFound.isPresent()) {
            Shop shopUpdate = shopFound.get();
            shopUpdate.setInVacations(shop.getInVacations());
            shopUpdate.setName(shop.getName());
            return shopRepository.save(shop);
        } else {
            return null;
        }
    }
	
	public String deleteShopById(long id) {
		shopRepository.deleteById(id);
        return "Shop "+ id +" deleted";
    }
}
