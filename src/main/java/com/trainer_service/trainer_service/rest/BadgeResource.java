package com.trainer_service.trainer_service.rest;

import com.trainer_service.trainer_service.objects.GymBadge;
import com.trainer_service.trainer_service.service.BadgeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/badge")
@RestController
@AllArgsConstructor
public class BadgeResource {

    private BadgeService badgeService;

    @GetMapping("/{id}")
    public GymBadge getBadgeById(@PathVariable("id") int id) {
        return badgeService.getById(id);
    }

    @GetMapping("/all")
    public List<GymBadge> getAllGymBadges() {
        return badgeService.getAllGymBadges();
    }

    @PostMapping
    public void createGymBadge() {
        badgeService.createGymBadge();
    }

    @DeleteMapping("/{id}")
    public void deleteGymBadge(@PathVariable("id") int id) {
        badgeService.deleteGymBadge(id);
    }

    //TODO Add payload and update endpoint
}
