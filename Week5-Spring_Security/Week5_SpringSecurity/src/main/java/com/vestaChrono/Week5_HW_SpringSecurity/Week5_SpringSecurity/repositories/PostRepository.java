package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.repositories;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
