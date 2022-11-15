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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/shops")
public class ShopController {
	
	@Autowired
    private ShopService service;
	
	@ApiOperation(value = "Get shop by id")
	@GetMapping("/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable long id) {
        return ResponseEntity.ok().body(service.getShopById(id));
    }
	
	@ApiOperation(value = "Get shops (sorting and filtering are possible)")
	@GetMapping
	public ResponseEntity<Page<Shop>> getAllShops(
		Pageable pageable,
		@ApiParam(example = "name") @RequestParam(required = false) Optional<String> sortBy,
		@ApiParam(example = "true") @RequestParam(required = false) Optional<Boolean> inVacations,
		@ApiParam(example = "2022-11-15") @RequestParam(required = false) Optional<String> createdAfter,
		@ApiParam(example = "2022-11-15") @RequestParam(required = false) Optional<String> createdBefore
		
	) {
		return ResponseEntity.ok(
			service.getShopList(sortBy, inVacations, createdAfter, createdBefore, pageable)
		);
	}
	
	@ApiOperation(value = "Create a shop")
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
	
	@ApiOperation(value = "Update a shop")
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
	
	@ApiOperation(value = "Delete a shop by its id")
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
