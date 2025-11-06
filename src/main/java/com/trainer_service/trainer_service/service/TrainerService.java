package com.trainer_service.trainer_service.service;

import com.trainer_service.trainer_service.objects.Trainer;
import com.trainer_service.trainer_service.objects.payload.UpdateTrainerPayload;
import com.trainer_service.trainer_service.persistence.TrainerDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class TrainerService {

    private final TrainerDao trainerDao;

    public TrainerService(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    public Trainer getTrainerById(int id) {
        return trainerDao.getTrainer(id);
    }

    public List<Trainer> getAllTrainers() {
        return trainerDao.getAllTrainers();
    }

    public Trainer updateTrainer(UpdateTrainerPayload updateTrainerPayload) {
        trainerDao.updateTrainer(updateTrainerPayload.getName(), updateTrainerPayload.getId());
        return trainerDao.getTrainer(updateTrainerPayload.getId());
    }
}
