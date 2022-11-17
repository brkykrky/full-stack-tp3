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

import fr.fullstack.shopapp.entity.Product;
import fr.fullstack.shopapp.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
    private ProductService service;
	
	@ApiOperation(value = "Get product by id")
	@GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        return ResponseEntity.ok().body(service.getProductById(id));
    }

	@ApiOperation(value = "Get products of a specific shop")
	@GetMapping
	public ResponseEntity<Page<Product>> getProductsOfShop(
		Pageable pageable,
		@ApiParam(example = "1") @RequestParam(required = false) Optional<Long> shopId,
		@ApiParam(example = "1") @RequestParam(required = false) Optional<Long> categoryId
	) {
		return ResponseEntity.ok(
			service.getShopProductList(shopId, categoryId, pageable)
		);
	}
	
	@ApiOperation(value = "Create a product")
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		try {
			return ResponseEntity.ok(service.createProduct(product));
		} catch (Exception e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "An error occured during the creation", e
			);
		}
	}
	
	@ApiOperation(value = "Update a product")
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		try {
			return ResponseEntity.ok().body(service.updateProduct(product));
		} catch (Exception e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "An error occured during the modification", e
			);
		}
	}
	
	@ApiOperation(value = "Delete a product by its id")
	@DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable long id) {
		try {
			service.deleteProductById(id);
			return HttpStatus.OK;
		} catch (Exception e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "An error occured during the deletion", e
			);
		}
    }
}
