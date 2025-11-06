package com.trainer_service.trainer_service.rest;

import com.trainer_service.trainer_service.error.ValidationException;
import com.trainer_service.trainer_service.objects.Trainer;
import com.trainer_service.trainer_service.objects.payload.UpdateTrainerPayload;
import com.trainer_service.trainer_service.service.TrainerService;
import org.springframework.web.bind.annotation.*;

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
    public Trainer updateTrainer(@PathVariable("id") int id, @RequestBody UpdateTrainerPayload updateTrainerPayload) {
        //TODO find out why Lombok getter isn't being picked up
        if (id != updateTrainerPayload.getId()) {
            throw new ValidationException("Ids do not match");
        }
        return trainerService.updateTrainer(updateTrainerPayload);
    }

    @PutMapping("/delete/{id}")
    public void deactivateTrainer(@PathVariable("id") int id) {
        trainerService.deactivateTrainer(id);
    }

}
