package com.trainer_service.trainer_service.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/trainer")
public class TrainerResource {

    public TrainerResource() {
    }

    @GetMapping("/{id}")
    public void getTrainerById(@PathVariable("id") int id) {
    }

    @GetMapping("/all")
    public void getAllTrainers() {
    }

    @PutMapping("{id}")
    public void updateTrainer(@PathVariable("id") int id) {
    }

    @PutMapping("/delete/{id}")
    public void deactivateTrainer(@PathVariable("id") int id) {
    }

}
