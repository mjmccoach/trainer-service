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
class BadgeDAOTest {
    private static final String SELECT_BY_ID = "SELECT * FROM gym_badges WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM gym_badges";
    private static final String CREATE = "INSERT into gym_badges VALUES(%s)";
    private static final String DELETE = "DELETE from gym_badges WHERE id = %s";
    private static final int BADGE_ID_1 = 1;
    private static final int BADGE_ID_2 = 2;
    private static final String BADGE_1_NAME = "Boulder Badge";


    @Mock
    JdbcTemplate mockJdbcTemplate;
    @Mock
    BadgeRowMapper mockBadgeRowMapper;

    @InjectMocks
    private BadgeDAO badgeDAO;

    private GymBadge badge1;
    private GymBadge badge2;


    @BeforeEach
    void setUp() {
        badge1 = new GymBadge(BADGE_ID_1, BadgeType.BOULDER);
        badge2 = new GymBadge(BADGE_ID_2, BadgeType.CASCADE);
    }

    @Test
    void select_by_id() {
        when(mockJdbcTemplate.queryForObject(eq(SELECT_BY_ID), eq(mockBadgeRowMapper), eq(BADGE_ID_1))).thenReturn(badge1);

        GymBadge actual = badgeDAO.getBadgeById(BADGE_ID_1);

        verify(mockJdbcTemplate).queryForObject(eq(SELECT_BY_ID), eq(mockBadgeRowMapper), eq(BADGE_ID_1));

        assertEquals(1, actual.getId());
        assertEquals(BadgeType.BOULDER, actual.getBadgeType());
    }

    @Test
    void select_all() {
        when(mockJdbcTemplate.query(eq(SELECT_ALL), eq(mockBadgeRowMapper))).thenReturn(Arrays.asList(badge1, badge2));

        List<GymBadge> actual = badgeDAO.getAllGymBadges();

        verify(mockJdbcTemplate).query(eq(SELECT_ALL), eq(mockBadgeRowMapper));

        assertEquals(1, actual.getFirst().getId());
        assertEquals(BadgeType.BOULDER, actual.getFirst().getBadgeType());

        assertEquals(2, actual.get(1).getId());
        assertEquals(BadgeType.CASCADE, actual.get(1).getBadgeType());
    }

    @Test
    void create() {
        badgeDAO.createGymBadge(BADGE_1_NAME);
        verify(mockJdbcTemplate).execute(String.format(CREATE, BADGE_1_NAME));
    }

    @Test
    void delete_gym_badge_by_id() {
        badgeDAO.deleteGymBadge(BADGE_ID_1);
        verify(mockJdbcTemplate).execute(String.format(DELETE, BADGE_ID_1));
    }
}