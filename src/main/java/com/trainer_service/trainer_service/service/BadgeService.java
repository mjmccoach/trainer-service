package com.trainer_service.trainer_service.service;

import com.trainer_service.trainer_service.enums.BadgeType;
import com.trainer_service.trainer_service.objects.GymBadge;
import com.trainer_service.trainer_service.persistence.BadgeDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class BadgeService {

    private BadgeDAO badgeDAO;

    public GymBadge getById(int id) {
        return badgeDAO.getBadgeById(id);
    }

    public List<GymBadge> getAllGymBadges() {
        return badgeDAO.getAllGymBadges();
    }

    public void createGymBadge(String name) {
        badgeDAO.createGymBadge(name);
    }

    public void deleteGymBadge(int id) {
        badgeDAO.deleteGymBadge(id);
    }

    //TODO Add payload and update endpoint

    public List<GymBadge> getBadgesByTrainerId(int id) {
        return Collections.singletonList(new GymBadge(1, BadgeType.BOULDER));
    }
}
