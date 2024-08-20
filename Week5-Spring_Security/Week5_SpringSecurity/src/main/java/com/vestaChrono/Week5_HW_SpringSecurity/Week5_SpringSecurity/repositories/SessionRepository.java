package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.repositories;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {

    void deleteByUserId(Long userId);
    Optional<SessionEntity> findByUserId(Long userId);


}
