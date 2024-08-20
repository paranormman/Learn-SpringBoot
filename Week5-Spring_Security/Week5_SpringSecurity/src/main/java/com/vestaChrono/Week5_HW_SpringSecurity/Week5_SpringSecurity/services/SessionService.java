package com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.services;

import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.entity.SessionEntity;
import com.vestaChrono.Week5_HW_SpringSecurity.Week5_SpringSecurity.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public void createSession(Long userId, String token) {
        sessionRepository.deleteByUserId(userId);
        SessionEntity session = new SessionEntity();
                session.setUserId(userId);
                session.setToken(token);
                sessionRepository.save(session);
    }

    public boolean isTokenValid(Long userId, String token) {
        Optional<SessionEntity> session = sessionRepository.findByUserId(userId);
        return session.isPresent() && session.get().getToken().equals(token);
    }

}
