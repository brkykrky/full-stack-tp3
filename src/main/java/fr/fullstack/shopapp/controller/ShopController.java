package fr.fullstack.shopapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.fullstack.shopapp.entity.Shop;
import fr.fullstack.shopapp.service.ShopService;

@RestController
@RequestMapping("/api/v1/shops")
public class ShopController {
	
	@Autowired
    private ShopService service;
	
	@GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable long id) {
        return ResponseEntity.ok().body(service.getShopById(id));
    }
	
	@GetMapping
	public ResponseEntity<List<Shop>> getAllShops() {
	    return ResponseEntity.ok(service.getShopList());
	}
	
	@PostMapping
	public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
	    return ResponseEntity.ok(service.createShop(shop));
	}
	
	@PutMapping
	public ResponseEntity<Shop> updateShop(@RequestBody Shop shop) {
	    return ResponseEntity.ok().body(service.updateShop(shop));
	}
	
	@DeleteMapping("/{id}")
    public HttpStatus deleteShop(@PathVariable long id) {
        service.deleteShopById(id);
        return HttpStatus.OK;
    }
}
