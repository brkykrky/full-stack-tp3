package fr.fullstack.shopapp.service;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.fullstack.shopapp.entity.Product;
import fr.fullstack.shopapp.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager em;

	public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

	public Page<Product> getShopProductList(Optional<Long> shopId, Optional<Long> categoryId, Pageable pageable) {       
        if (shopId.isPresent() && categoryId.isPresent()) {
            return productRepository.findByShopAndCategory(shopId.get(), categoryId.get(), pageable);
        }

        if (shopId.isPresent()) {
            return productRepository.findByShop(shopId.get(), pageable);
        }
        
        return productRepository.findAll(pageable);
    }
	
    @Transactional
	public Product createProduct(Product product) throws Exception  {
        // Check that product exists at least in french
        boolean cond = product.getLocalizedProducts()
            .stream().filter(o -> o.getLocale().equals("FR")).findFirst().isPresent();
        if (!cond) {
            throw new Exception("Product does not exist in French");
        }

        try {
            Product newProduct = productRepository.save(product);
            em.flush();
            em.refresh(newProduct);
            return newProduct;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
	
    @Transactional
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
