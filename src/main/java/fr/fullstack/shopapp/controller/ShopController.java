package fr.fullstack.shopapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
	public ResponseEntity<Page<Shop>> getAllShops(
		Pageable pageable,
		@RequestParam Optional<String> sortBy,
		@RequestParam Optional<Boolean> inVacations,
		@RequestParam Optional<String> createdAfter,
		@RequestParam Optional<String> createdBefore
		
	) {
		return ResponseEntity.ok(
			service.getShopList(sortBy, inVacations, createdAfter, createdBefore, pageable)
		);
	}
	
	@PostMapping
	public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
		try {
			return ResponseEntity.ok(service.createShop(shop));
		} catch (Exception e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "An error occured during the creation", e
			);
		}
	}
	
	@PutMapping
	public ResponseEntity<Shop> updateShop(@RequestBody Shop shop) {
		try {
			return ResponseEntity.ok().body(service.updateShop(shop));
		} catch (Exception e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "An error occured during the modification", e
			);
		}
	}
	
	@DeleteMapping("/{id}")
    public HttpStatus deleteShop(@PathVariable long id) {
		try {
			service.deleteShopById(id);
			return HttpStatus.OK;
		} catch (Exception e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "An error occured during the deletion", e
			);
		}
    }
}
