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

    @Mock
    JdbcTemplate mockJdbcTemplate;
    @Mock
    BadgeRowMapper mockBadgeRowMapper;

    @InjectMocks
    private BadgeDAO badgeDAO;

    private GymBadge badge1;

    @BeforeEach
    void setUp() {
        badge1 = new GymBadge(BADGE_ID_1, BadgeType.BOULDER);
    }

    @Test
    void select_by_id() {
        when(mockJdbcTemplate.queryForObject(eq(SELECT_BY_ID), eq(mockBadgeRowMapper), eq(BADGE_ID_1))).thenReturn(badge1);

        GymBadge actual = badgeDAO.getBadgeById(BADGE_ID_1);

        verify(mockJdbcTemplate).queryForObject(eq(SELECT_BY_ID), eq(mockBadgeRowMapper), eq(BADGE_ID_1));
        
        assertEquals(1, actual.getId());
        assertEquals(BadgeType.BOULDER, actual.getBadgeType());
    }
}