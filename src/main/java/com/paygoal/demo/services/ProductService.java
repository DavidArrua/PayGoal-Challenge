package com.paygoal.demo.services;

import com.paygoal.demo.models.Product;

public interface ProductService {

    public void saveProduct(Product product);

    public void deleteProduct(Long id);

    public Product findById(Long id);

    public void updateProduct(Long productId, Product updatedProduct);
}
