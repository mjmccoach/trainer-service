package com.trainer_service.trainer_service.persistence;

import com.trainer_service.trainer_service.enums.BadgeType;
import com.trainer_service.trainer_service.objects.GymBadge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TrainerBadgeDAOTest {
    private static final String SELECT_ALL_BY_TRAINER = "SELECT * FROM trainer_badges WHERE id = ?";
    private static final int BADGE_ID_1 = 1;
    private static final int BADGE_ID_2 = 2;
    private static final int TRAINER_1_ID = 20;

    @Mock
    JdbcTemplate mockJdbcTemplate;
    @Mock
    BadgeRowMapper mockBadgeRowMapper;

    @InjectMocks
    TrainerBadgeDAO trainerBadgeDAO;

    private GymBadge badge1;
    private GymBadge badge2;

    @BeforeEach
    void setUp() {
        badge1 = new GymBadge(BADGE_ID_1, BadgeType.BOULDER);
        badge2 = new GymBadge(BADGE_ID_2, BadgeType.CASCADE);
    }

    @Test
    void select_all_by_trainer_id() {
        when(mockJdbcTemplate.query(eq(SELECT_ALL_BY_TRAINER), eq(mockBadgeRowMapper), eq(TRAINER_1_ID))).thenReturn(Arrays.asList(badge1, badge2));

        List<GymBadge> actual = trainerBadgeDAO.getAllBadgesByTrainerId(TRAINER_1_ID);

        verify(mockJdbcTemplate).query(SELECT_ALL_BY_TRAINER, mockBadgeRowMapper, TRAINER_1_ID);

        assertEquals(2, actual.size());

        assertEquals(1, actual.getFirst().getId());
        assertEquals(BadgeType.BOULDER, actual.getFirst().getBadgeType());

        assertEquals(2, actual.get(1).getId());
        assertEquals(BadgeType.CASCADE, actual.get(1).getBadgeType());
    }
}