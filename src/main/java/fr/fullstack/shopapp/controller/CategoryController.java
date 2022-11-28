package fr.fullstack.shopapp.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.fullstack.shopapp.entity.Category;
import fr.fullstack.shopapp.service.CategoryService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
    private CategoryService service;

	@ApiOperation(value = "Get shop by id")
	@GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
        return ResponseEntity.ok().body(service.getCategoryById(id));
    }
	
	@ApiOperation(value = "Get shops (sorting and filtering are possible)")
	@GetMapping
	public ResponseEntity<Page<Category>> getAllCategorys(Pageable pageable) {
		return ResponseEntity.ok(service.getCategoryList(pageable));
	}
	
	@ApiOperation(value = "Create a shop")
	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category shop) {
		try {
			return ResponseEntity.ok(service.createCategory(shop));
		} catch (Exception e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "An error occured during the creation", e
			);
		}
	}
	
	@ApiOperation(value = "Update a shop")
	@PutMapping
	public ResponseEntity<Category> updateCategory(@RequestBody Category shop) {
		try {
			return ResponseEntity.ok().body(service.updateCategory(shop));
		} catch (Exception e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "An error occured during the modification", e
			);
		}
	}
	
	@ApiOperation(value = "Delete a shop by its id")
	@DeleteMapping("/{id}")
    public HttpStatus deleteCategory(@PathVariable long id) {
		try {
			service.deleteCategoryById(id);
			return HttpStatus.OK;
		} catch (Exception e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "An error occured during the deletion", e
			);
		}
    }
}