package com.trainer_service.trainer_service.rest;

import com.trainer_service.trainer_service.enums.BadgeType;
import com.trainer_service.trainer_service.objects.GymBadge;
import com.trainer_service.trainer_service.service.BadgeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
class BadgeResourceTest {
    private static final int BADGE_ID_1 = 1;
    private static final int BADGE_ID_2 = 2;
    private static final String BADGE_1_NAME = "Boulder Badge";

    @Mock
    BadgeService mockBadgeService;

    @InjectMocks
    private BadgeResource badgeResource;

    private GymBadge badge1;
    private GymBadge badge2;

    @BeforeEach
    void setUp() {
        badge1 = new GymBadge(BADGE_ID_1, BadgeType.BOULDER);
        badge2 = new GymBadge(BADGE_ID_2, BadgeType.CASCADE);
    }

    @Test
    void select_by_id() {
        when(mockBadgeService.getById(anyInt())).thenReturn(badge1);

        GymBadge actual = badgeResource.getBadgeById(BADGE_ID_1);

        assertEquals(BADGE_ID_1, actual.getId());
        assertEquals(BadgeType.BOULDER, actual.getBadgeType());
    }

    @Test
    void select_all() {
        when(mockBadgeService.getAllGymBadges()).thenReturn(Arrays.asList(badge1, badge2));

        List<GymBadge> actual = badgeResource.getAllGymBadges();

        assertEquals(1, actual.getFirst().getId());
        assertEquals(BadgeType.BOULDER, actual.getFirst().getBadgeType());

        assertEquals(2, actual.get(1).getId());
        assertEquals(BadgeType.CASCADE, actual.get(1).getBadgeType());
    }

    @Test
    void create() {
        badgeResource.createGymBadge(BADGE_1_NAME);
        verify(mockBadgeService).createGymBadge(BADGE_1_NAME);
    }

    @Test
    void delete_by_gym_badge_id() {
        badgeResource.deleteGymBadge(BADGE_ID_1);
        verify(mockBadgeService).deleteGymBadge(BADGE_ID_1);
    }
}