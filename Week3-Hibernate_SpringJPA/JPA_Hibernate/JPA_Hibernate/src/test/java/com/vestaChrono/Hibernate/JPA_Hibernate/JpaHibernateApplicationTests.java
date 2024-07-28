package com.vestaChrono.Hibernate.JPA_Hibernate;

import com.vestaChrono.Hibernate.JPA_Hibernate.entities.ProductEntity;
import com.vestaChrono.Hibernate.JPA_Hibernate.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaHibernateApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	@Test
    void testRepository() {
		ProductEntity productEntities = ProductEntity.builder()
				.sku("nestle234")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(123.45))
				.quantity(12)
				.build();
		ProductEntity savedProductEntity = productRepository.save(productEntities);
		System.out.println(savedProductEntity);
	}

	@Test
	void getRepository() {
//		List<ProductEntity> entities = productRepository.findAll();

//		List<ProductEntity> entities = productRepository.findByCreatedAtAfter(
//				LocalDateTime.of(2024, 1, 1, 0,0,0)
//		);

//		List<ProductEntity> newEntities = productRepository.findByQuantityGreaterThanOrPriceLessThan(4, BigDecimal.valueOf(24.35));

//		List<ProductEntity> entities = productRepository.findByTitleLike("%mazza%");

//		List<ProductEntity> newEntities = productRepository.findByQuantityGreaterThanOrPriceLessThan(4, BigDecimal.valueOf(24.35));

//		List<ProductEntity> entities = productRepository.findByTitle("Pepsi");

//		List<ProductEntity> entities = productRepository.findByTitleContaining("choco");

		List<ProductEntity> entities = productRepository.findByTitleContainingIgnoreCase("choco", null);
		System.out.println(entities);
	}

	@Test
	void getSingleFromRepository() {
		Optional<ProductEntity> productEntity = productRepository
				.findByTitleAndPrice("pepsi", BigDecimal.valueOf(14.4));
		productEntity.ifPresent(System.out::println);
	}

}
