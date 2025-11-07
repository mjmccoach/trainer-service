package com.trainer_service.trainer_service.service;

import com.trainer_service.trainer_service.objects.Trainer;
import com.trainer_service.trainer_service.objects.payload.UpdateTrainerPayload;
import com.trainer_service.trainer_service.persistence.TrainerDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TrainerServiceTest {

    private static final int ID = 250;
    private static final String NAME = "Lance";
    private static final int ID_2 = 251;
    private static final String NAME_2 = "Lorelei";

    private TrainerService trainerService;
    private Trainer trainer1;
    private Trainer trainer2;

    @Mock
    private TrainerDao mockTrainerDao;

    @BeforeEach
    void setUp() {
        trainerService = new TrainerService(mockTrainerDao);
        trainer1 = new Trainer(ID, NAME);
        trainer2 = new Trainer(ID_2, NAME_2);

    }

    @Test
    void get_trainer_by_id() {
        when(mockTrainerDao.getTrainer(anyInt())).thenReturn(trainer1);

        Trainer actual = trainerService.getTrainerById(ID);

        assertEquals(ID, actual.getId());
        assertEquals(NAME, actual.getName());

    }

    @Test
    void get_all_trainers() {
        List<Trainer> trainers = Arrays.asList(trainer1, trainer2);

        when(mockTrainerDao.getAllTrainers()).thenReturn(trainers);

        List<Trainer> actual = trainerService.getAllTrainers();

        assertEquals(ID, actual.getFirst().getId());
        assertEquals(NAME, actual.getFirst().getName());
        assertEquals(ID_2, actual.get(1).getId());
        assertEquals(NAME_2, actual.get(1).getName());
    }

    @Test
    void update_trainer() {
        String updatedName = "Bruno";
        UpdateTrainerPayload updateTrainerPayload = new UpdateTrainerPayload(ID, updatedName);
        trainer1.setName(updatedName);

        when(mockTrainerDao.getTrainer(ID)).thenReturn(trainer1);

        Trainer actual = trainerService.updateTrainer(updateTrainerPayload);

        assertEquals(ID, actual.getId());
        assertEquals(updatedName, actual.getName());

    }

    @Test
    void deactivate_trainer() {
        trainerService.deactivateTrainer(ID);

        verify(mockTrainerDao).deactivateTrainer(ID);

    }
}
