package com.trainer_service.trainer_service.resource;

import com.trainer_service.trainer_service.error.ValidationException;
import com.trainer_service.trainer_service.objects.Trainer;
import com.trainer_service.trainer_service.objects.payload.UpdateTrainerPayload;
import com.trainer_service.trainer_service.rest.TrainerResource;
import com.trainer_service.trainer_service.service.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TrainerResourceTest {

    private static final int ID = 250;
    private static final String NAME = "Lance";
    private static final int ID_2 = 251;
    private static final String NAME_2 = "Lorelei";


    private TrainerResource trainerResource;
    private Trainer trainer1;
    private Trainer trainer2;

    @Mock
    private TrainerService mockTrainerService;

    @BeforeEach
    void setUp() {
        trainerResource = new TrainerResource(mockTrainerService);
        trainer1 = new Trainer(ID, NAME, Collections.emptyList(), Collections.emptyList());
        trainer2 = new Trainer(ID_2, NAME_2, Collections.emptyList(), Collections.emptyList());

    }

    @Test
    void get_trainer_by_id() {
        when(mockTrainerService.getTrainerById(anyInt())).thenReturn(trainer1);

        Trainer actual = trainerResource.getTrainerById(ID);

        assertEquals(ID, actual.getId());
        assertEquals(NAME, actual.getName());
    }

    @Test
    void get_trainers() {
        List<Trainer> trainers = Arrays.asList(trainer1, trainer2);

        when(mockTrainerService.getAllTrainers()).thenReturn(trainers);

        List<Trainer> actual = trainerResource.getAllTrainers();

        assertEquals(ID, actual.getFirst().getId());
        assertEquals(NAME, actual.getFirst().getName());
        assertEquals(ID_2, actual.get(1).getId());
        assertEquals(NAME_2, actual.get(1).getName());
    }

    @Test
    void update_trainer_ids_dont_match() {
        UpdateTrainerPayload updateTrainerPayload = new UpdateTrainerPayload(ID, NAME);
        assertThrows(ValidationException.class, () -> trainerResource.updateTrainer(ID_2, updateTrainerPayload));
    }

    @Test
    void update_trainer() {
        String updatedName = "Bruno";
        UpdateTrainerPayload updateTrainerPayload = new UpdateTrainerPayload(ID, updatedName);
        trainer1.setName(updatedName);

        when(mockTrainerService.updateTrainer(updateTrainerPayload)).thenReturn(trainer1);

        Trainer actual = trainerResource.updateTrainer(ID, updateTrainerPayload);

        assertEquals(ID, actual.getId());
        assertEquals(updatedName, actual.getName());
    }

    @Test
    void deactivateTrainer() {
        trainerResource.deactivateTrainer(ID);

        verify(mockTrainerService).deactivateTrainer(ID);
    }

}
