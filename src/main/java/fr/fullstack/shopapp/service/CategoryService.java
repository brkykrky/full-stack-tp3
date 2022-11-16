package fr.fullstack.shopapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.fullstack.shopapp.entity.Category;
import fr.fullstack.shopapp.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
    private CategoryRepository categoryRepository;

	public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

	public Page<Category> getCategoryList(Pageable pageable) {       
        return categoryRepository.findAll(pageable);
    }
	
	public Category createCategory(Category category) throws Exception  {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
	
	public Category updateCategory(Category category) throws Exception {
        Optional<Category> categoryFound = categoryRepository.findById(category.getId());
        if (categoryFound.isPresent()) {
            return this.createCategory(category);
        } else {
            throw new Exception("Id not found");
        }
    }
	
	public void deleteCategoryById(long id) throws Exception {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
