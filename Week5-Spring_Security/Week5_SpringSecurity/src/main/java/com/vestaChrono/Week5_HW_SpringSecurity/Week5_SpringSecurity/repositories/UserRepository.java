package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.repositories;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
