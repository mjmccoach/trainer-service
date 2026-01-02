package com.trainer_service.trainer_service.rest;

import com.trainer_service.trainer_service.enums.BadgeType;
import com.trainer_service.trainer_service.objects.GymBadge;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequestMapping("/badge")
@RestController
public class BadgeResource {

    @GetMapping("/{id}")
    public GymBadge getBadgeById(int id) {
        return new GymBadge(1, BadgeType.VOLCANO);
    }

    @GetMapping("/all")
    public List<GymBadge> getAllGymBadges() {
        return Collections.singletonList(new GymBadge(1, BadgeType.BOULDER));
    }

    @PostMapping
    public void createGymBadge() {
    }

    @DeleteMapping("/{id}")
    public void deleteGymBadge(int id) {
    }

    //TODO Add payload and update endpoint
}
