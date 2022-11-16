package fr.fullstack.shopapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

	public Page<Product> getShopProductList(long shopId, Pageable pageable) {       
        List<Product> list = productRepository.findByShop(shopId, pageable);
        return new PageImpl<Product>(list);
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
