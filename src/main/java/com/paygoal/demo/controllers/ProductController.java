package com.paygoal.demo.controllers;

import com.paygoal.demo.models.Product;
import com.paygoal.demo.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;


    @Operation(summary = "Retrieves a product by its ID", description = "Returns the details of a specific product based on its unique identifier")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.findById(id);
            if (product == null) {
                return new ResponseEntity<>("Product with ID " + id + " not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Creates a new product", description = "Adds a new product to the system with the provided information")
    @PostMapping("/create")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errorMessages = new ArrayList<>();
            fieldErrors.forEach(fieldError -> errorMessages.add(fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        try {
            productService.saveProduct(product);
            return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Updates an existing product", description = "Modifies the details of an existing product based on the given parameters")
    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @Valid @RequestBody Product updatedProduct, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<String> errorMessages = new ArrayList<>();
            fieldErrors.forEach(fieldError -> errorMessages.add(fieldError.getDefaultMessage()));
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        try {
            Product product = productService.findById(id);
            if (product == null) {
                return new ResponseEntity<>("Product with ID " + id + " not found", HttpStatus.NOT_FOUND);
            }

            productService.updateProduct(id,updatedProduct);
            return new ResponseEntity<>("Updated successfully", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Deletes a product", description = "Removes a product from the system based on its ID")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        try {
            Product product = productService.findById(id);
            if (product == null) {
                return new ResponseEntity<>("Product with ID " + id + " not found", HttpStatus.NOT_FOUND);
            }

            productService.deleteProduct(id);
            return new ResponseEntity<>("Product with ID " + id + " deleted successfully", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Retrieves all products sorted by price", description = "Returns a list of all products available in the system, sorted in ascending order by their respective prices")
    @GetMapping("/all")
    public ResponseEntity<Object> getAllProductsOrderByPrice() {
        try {
            List<Product> products = productService.getAllProductsOrderByPrice();
            if (products.isEmpty()) {
                return new ResponseEntity<>("No products found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
