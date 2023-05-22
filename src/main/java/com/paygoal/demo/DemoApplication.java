package com.paygoal.demo;

import com.paygoal.demo.models.Product;
import com.paygoal.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public CommandLineRunner initData(ProductRepository productRepository) {
		return (args) -> {
			Product product1 = new Product("Test product", "-", 20.00, 20);
			productRepository.save(product1);

			Product product2 = new Product("Product 2", "-", 900.00, 120);
			productRepository.save(product2);

		};
	}

}
