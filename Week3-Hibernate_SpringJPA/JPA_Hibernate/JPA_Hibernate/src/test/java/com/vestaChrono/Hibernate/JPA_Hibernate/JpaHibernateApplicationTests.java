package com.vestaChrono.Hibernate.JPA_Hibernate;

import com.vestaChrono.Hibernate.JPA_Hibernate.entities.ProductEntity;
import com.vestaChrono.Hibernate.JPA_Hibernate.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

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

}
