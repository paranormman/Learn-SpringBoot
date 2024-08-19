package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.repositories;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
