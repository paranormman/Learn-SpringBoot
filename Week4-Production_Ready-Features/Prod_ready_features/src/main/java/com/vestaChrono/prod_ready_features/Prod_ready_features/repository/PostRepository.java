package com.vestaChrono.prod_ready_features.Prod_ready_features.repository;

import com.vestaChrono.prod_ready_features.Prod_ready_features.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
