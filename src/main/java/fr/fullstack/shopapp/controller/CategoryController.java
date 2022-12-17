package fr.fullstack.shopapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fr.fullstack.shopapp.model.Category;
import fr.fullstack.shopapp.service.CategoryService;
import fr.fullstack.shopapp.util.ErrorValidation;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	@Autowired
    private CategoryService service;

	@ApiOperation(value = "Get category by id")
	@GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable long id) {
		try {
			return ResponseEntity.ok().body(service.getCategoryById(id));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
    }
	
	@ApiOperation(value = "Get categories (sorting and filtering are possible)")
	@GetMapping
	public ResponseEntity<Page<Category>> getAllCategorys(Pageable pageable) {
		return ResponseEntity.ok(service.getCategoryList(pageable));
	}
	
	@ApiOperation(value = "Create a category")
	@PostMapping
	public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category, Errors errors) {
		if (errors.hasErrors()) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, ErrorValidation.getErrorValidationMessage(errors));
		}

		try {
			return ResponseEntity.ok(service.createCategory(category));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Update a category")
	@PutMapping
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody Category category, Errors errors) {
		if (errors.hasErrors()) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, ErrorValidation.getErrorValidationMessage(errors));
		}
		
		try {
			return ResponseEntity.ok().body(service.updateCategory(category));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Delete a category by its id")
	@DeleteMapping("/{id}")
    public HttpStatus deleteCategory(@PathVariable long id) {
		try {
			service.deleteCategoryById(id);
			return HttpStatus.OK;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
    }
}
