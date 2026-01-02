package com.trainer_service.trainer_service.service;

import com.trainer_service.trainer_service.enums.BadgeType;
import com.trainer_service.trainer_service.objects.GymBadge;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class BadgeService {

    public GymBadge getById(int id) {
        return new GymBadge(1, BadgeType.BOULDER);
    }

    public List<GymBadge> getAllGymBadges() {
        return Collections.singletonList(new GymBadge(1, BadgeType.BOULDER));
    }

    public void createGymBadge() {
    }

    public void deleteGymBadge(int id) {
    }

    //TODO Add payload and update endpoint

    public List<GymBadge> getBadgesByTrainerId(int id) {
        return Collections.singletonList(new GymBadge(1, BadgeType.BOULDER));
    }
}
