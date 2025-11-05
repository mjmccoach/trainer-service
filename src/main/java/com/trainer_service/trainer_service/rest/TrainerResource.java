package com.trainer_service.trainer_service.rest;

import com.trainer_service.trainer_service.objects.Trainer;
import com.trainer_service.trainer_service.service.TrainerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/trainer")
public class TrainerResource {
    private final TrainerService trainerService;

    public TrainerResource(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping("/{id}")
    public Trainer getTrainerById(@PathVariable("id") int id) {
        return trainerService.getTrainerById(id);
    }

    @GetMapping("/all")
    public List<Trainer> getAllTrainers() {
        return trainerService.getAllTrainers();
    }

    @PutMapping("{id}")
    public void updateTrainer(@PathVariable("id") int id) {
    }

    @PutMapping("/delete/{id}")
    public void deactivateTrainer(@PathVariable("id") int id) {
    }

}
