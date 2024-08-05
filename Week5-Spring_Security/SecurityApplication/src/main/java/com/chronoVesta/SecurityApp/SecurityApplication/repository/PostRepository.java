package com.chronoVesta.SecurityApp.SecurityApplication.repository;

import com.chronoVesta.SecurityApp.SecurityApplication.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
