package com.chronoVesta.SecurityApp.SecurityApplication.service;

import com.chronoVesta.SecurityApp.SecurityApplication.entity.User;
import com.chronoVesta.SecurityApp.SecurityApplication.entity.enums.SubscriptionPlan;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionService {
    public boolean hasAccessBasedOnSubscription(User user, String requiredPlan) {
        SubscriptionPlan userPlan = user.getSubscriptionPlan();
        return userPlan.name().equals(requiredPlan) || userPlan.ordinal() >= SubscriptionPlan.valueOf(requiredPlan).ordinal();
    }
}
