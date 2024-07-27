package com.vestaChrono.Hibernate.JPA_Hibernate.repository;

import com.vestaChrono.Hibernate.JPA_Hibernate.entities.ProductEntity;
import com.vestaChrono.Hibernate.JPA_Hibernate.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByTitle(String title);

//    List<ProductEntity> findByCreatedAtAfter(LocalDateTime after);


//    List<ProductEntity> findByQuantityGreaterThanOrPriceLessThan(int quantity, BigDecimal price);

    List<ProductEntity> findByTitleLike(String title);

    List<ProductEntity> findByTitleContaining(String title);

    List<ProductEntity> findByTitleContainingIgnoreCase(String title);

//    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    //    Creating a Custom Query or (e.title=?1 AND e.price=?2)
    @Query("SELECT e FROM ProductEntity e WHERE e.title=:title AND e.price=:price")
    Optional<ProductEntity> findByTitleAndPrice(String title, BigDecimal price);

    List<ProductEntity> findByTitleOrderByPrice(String title);

    List<ProductEntity> findByOrderByPrice();
}
