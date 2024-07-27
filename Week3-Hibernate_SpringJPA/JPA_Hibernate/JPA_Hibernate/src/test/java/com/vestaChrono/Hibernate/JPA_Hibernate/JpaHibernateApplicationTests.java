package com.vestaChrono.Hibernate.JPA_Hibernate;

import com.vestaChrono.Hibernate.JPA_Hibernate.entities.ProductEntity;
import com.vestaChrono.Hibernate.JPA_Hibernate.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
	void getRepositories() {
//		List<ProductEntity> entities = productRepository.findAll();

//		List<ProductEntity> entities = productRepository.findByTitle("Pepsi");

//		List<ProductEntity> entities = productRepository.findByTitleLike("Mazza");

//		List<ProductEntity> entities = productRepository.findByTitleOrderByPrice("Pringles");

//		Optional<ProductEntity> entities = productRepository.findByTitleAndPrice("Cheetos", BigDecimal.valueOf(23.4));

		List<ProductEntity> entities = productRepository.findByCreatedAtAfter(
				LocalDateTime.of(2024, 1, 1, 0, 0, 0));
		System.out.println(entities);
	}

	@Test
	void getSingleFromRepository() {
		Optional<ProductEntity> entities = productRepository.findByTitleAndPrice("7UP", BigDecimal.valueOf(19.7));
		entities.ifPresent(System.out::println);
	}

}
