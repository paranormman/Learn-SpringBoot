package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String token;

}
