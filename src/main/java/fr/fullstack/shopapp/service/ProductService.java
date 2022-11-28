package fr.fullstack.shopapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.fullstack.shopapp.entity.Product;
import fr.fullstack.shopapp.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
    private ProductRepository productRepository;

	public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

	public Page<Product> getShopProductList(Optional<Long> shopId, Optional<Long> categoryId, Pageable pageable) {       
        if (shopId.isPresent()) {
            return productRepository.findByShop(shopId.get(), pageable);
        }

        if (shopId.isPresent() && categoryId.isPresent()) {
            return productRepository.findByShopAndCategory(shopId.get(), categoryId.get(), pageable);
        }
        
        return productRepository.findAll(pageable);
    }
	
	public Product createProduct(Product product) throws Exception  {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
	
	public Product updateProduct(Product product) throws Exception {
        Optional<Product> productFound = productRepository.findById(product.getId());
        if (productFound.isPresent()) {
            return this.createProduct(product);
        } else {
            throw new Exception("Id not found");
        }
    }
	
	public void deleteProductById(long id) throws Exception {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
