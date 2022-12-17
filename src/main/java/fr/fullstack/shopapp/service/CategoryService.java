package fr.fullstack.shopapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.fullstack.shopapp.model.Category;
import fr.fullstack.shopapp.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
    private CategoryRepository categoryRepository;

	public Category getCategoryById(long id) throws Exception {
        try {
            return getCategory(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
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
        try {
            getCategory(category.getId());
            return this.createCategory(category);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
	
	public void deleteCategoryById(long id) throws Exception {
        try {
            getCategory(id);
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private Category getCategory(Long id) throws Exception {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new Exception("Category with id " + id + " not found");
        }
        return category.get();
    }
}
