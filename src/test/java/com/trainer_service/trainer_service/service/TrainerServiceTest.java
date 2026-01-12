package com.trainer_service.trainer_service.service;

import com.trainer_service.trainer_service.enums.BadgeType;
import com.trainer_service.trainer_service.objects.GymBadge;
import com.trainer_service.trainer_service.objects.Item;
import com.trainer_service.trainer_service.objects.Trainer;
import com.trainer_service.trainer_service.objects.payload.UpdateTrainerPayload;
import com.trainer_service.trainer_service.persistence.TrainerBadgeDAO;
import com.trainer_service.trainer_service.persistence.TrainerDao;
import com.trainer_service.trainer_service.persistence.TrainerItemDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
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
    private static final int BADGE_ID_1 = 1;
    private static final int BADGE_ID_2 = 2;
    private static final int ITEM_ID_1 = 12;
    private static final String ITEM_1_NAME = "Pokeball";
    private static final int ITEM_1_COUNT = 5;
    private static final int ITEM_ID_2 = 24;
    private static final String ITEM_2_NAME = "Repel";
    private static final int ITEM_2_COUNT = 10;
    @Mock
    TrainerDao mockTrainerDao;
    @Mock
    TrainerBadgeDAO mockTrainerBadgeDao;
    @Mock
    TrainerItemDAO mockTrainerItemDAO;
    private Trainer trainer1;
    private Trainer trainer2;
    private GymBadge badge1;
    private GymBadge badge2;
    private Item item1;
    private Item item2;
    @InjectMocks
    private TrainerService trainerService;


    @BeforeEach
    void setUp() {
        badge1 = new GymBadge(BADGE_ID_1, BadgeType.BOULDER);
        badge2 = new GymBadge(BADGE_ID_2, BadgeType.CASCADE);
        item1 = new Item(ITEM_ID_1, ITEM_1_NAME, ITEM_1_COUNT);
        item2 = new Item(ITEM_ID_2, ITEM_2_NAME, ITEM_2_COUNT);
        trainer1 = new Trainer(ID, NAME, Collections.emptyList(), Collections.emptyList());
        trainer2 = new Trainer(ID_2, NAME_2, Collections.emptyList(), Collections.emptyList());

    }

    @Test
    void get_trainer_by_id() {
        when(mockTrainerDao.getTrainer(anyInt())).thenReturn(trainer1);
        when(mockTrainerBadgeDao.getAllBadgesByTrainerId(anyInt())).thenReturn(Arrays.asList(badge1, badge2));
        when(mockTrainerItemDAO.getAllItemsByTrainer(anyInt())).thenReturn(Arrays.asList(item1, item2));
        Trainer actual = trainerService.getTrainerById(ID);

        assertEquals(ID, actual.getId());
        assertEquals(NAME, actual.getName());

        assertEquals(BADGE_ID_1, actual.getBadges().getFirst().getId());
        assertEquals(BadgeType.BOULDER, actual.getBadges().getFirst().getBadgeType());

        assertEquals(ITEM_ID_1, actual.getItems().getFirst().getId());
        assertEquals(ITEM_1_NAME, actual.getItems().getFirst().getName());
        assertEquals(ITEM_1_COUNT, actual.getItems().getFirst().getCount());


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
        when(mockTrainerBadgeDao.getAllBadgesByTrainerId(anyInt())).thenReturn(Arrays.asList(badge1, badge2));

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
