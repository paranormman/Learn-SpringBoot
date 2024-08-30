package com.chronoVesta.SecurityApp.SecurityApplication.service;

import com.chronoVesta.SecurityApp.SecurityApplication.entity.Session;
import com.chronoVesta.SecurityApp.SecurityApplication.entity.User;
import com.chronoVesta.SecurityApp.SecurityApplication.entity.enums.SubscriptionPlan;
import com.chronoVesta.SecurityApp.SecurityApplication.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
//    private final int SESSION_LIMIT = 2;  // Hard coded session limit

    public void generateNewSession(User user, String refreshToken) {

        SubscriptionPlan subscriptionPlan = user.getSubscriptionPlan();
        int sessionLimit = 0;
        if (subscriptionPlan.equals(SubscriptionPlan.FREE)) {
            sessionLimit = 1;
        } else if (subscriptionPlan.equals(SubscriptionPlan.BASIC)) {
            sessionLimit = 2;
        } else {
            sessionLimit = 3;
        }

        List<Session> userSessions = sessionRepository.findByUser(user);
        if (userSessions.size() == sessionLimit) {
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));

            Session leastRecentlyUsedSession = userSessions.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);
        }

        Session newSession = Session.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();
        sessionRepository.save(newSession);
    }

    public void validateRefreshToken(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session not found for the refresh token: " + refreshToken));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }

    public void removeSession(String refreshToken) {
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new SessionAuthenticationException("Session not found for refreshToken: " + refreshToken));
        sessionRepository.deleteById(session.getId());

    }
}
