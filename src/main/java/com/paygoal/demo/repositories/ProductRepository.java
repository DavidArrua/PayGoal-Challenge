package com.paygoal.demo.repositories;

import com.paygoal.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional<Product> findById(Long id);
}
