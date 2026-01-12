package com.trainer_service.trainer_service.service;

import com.trainer_service.trainer_service.objects.Trainer;
import com.trainer_service.trainer_service.objects.payload.UpdateTrainerPayload;
import com.trainer_service.trainer_service.persistence.TrainerBadgeDAO;
import com.trainer_service.trainer_service.persistence.TrainerDao;
import com.trainer_service.trainer_service.persistence.TrainerItemDAO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class TrainerService {

    private final TrainerDao trainerDao;
    private final TrainerBadgeDAO trainerBadgeDAO;
    private final TrainerItemDAO trainerItemDAO;

    public Trainer getTrainerById(int id) {
        Trainer trainer = trainerDao.getTrainer(id);
        trainer.setBadges(trainerBadgeDAO.getAllBadgesByTrainerId(id));
        trainer.setItems(trainerItemDAO.getAllItemsByTrainer(id));
        return trainer;
    }

    public List<Trainer> getAllTrainers() {
        return trainerDao.getAllTrainers();
    }

    public Trainer updateTrainer(UpdateTrainerPayload updateTrainerPayload) {
        trainerDao.updateTrainer(updateTrainerPayload.getName(), updateTrainerPayload.getId());
        return getTrainerById(updateTrainerPayload.getId());
    }

    public void deactivateTrainer(int id) {
        trainerDao.deactivateTrainer(id);
    }
}
