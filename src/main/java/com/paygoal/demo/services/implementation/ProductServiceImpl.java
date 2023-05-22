package com.paygoal.demo.services.implementation;

import com.paygoal.demo.models.Product;
import com.paygoal.demo.repositories.ProductRepository;
import com.paygoal.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void updateProduct(Long productId, Product updatedProduct) {

        if (updatedProduct == null) {
            throw new IllegalArgumentException("Updated product cannot be null");
        }

        Product product = findById(productId);
        if (product == null) {
            throw new NoSuchElementException("Product with ID " + productId + " not found");
        }

        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());

        saveProduct(product);
    }
}
