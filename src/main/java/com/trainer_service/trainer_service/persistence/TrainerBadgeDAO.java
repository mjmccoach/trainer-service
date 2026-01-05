package com.trainer_service.trainer_service.persistence;

import com.trainer_service.trainer_service.objects.GymBadge;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TrainerBadgeDAO {
    private static final String SELECT_ALL_BY_TRAINER = "SELECT * FROM trainer_badges WHERE id = ?";
    private static final String CREATE_TRAINER_BADGE = "INSERT into trainer_badges VALUES(%s, %s)";
    private JdbcTemplate jdbcTemplate;
    private BadgeRowMapper badgeRowMapper;

    public List<GymBadge> getAllBadgesByTrainerId(int trainerId) {
        return jdbcTemplate.query(SELECT_ALL_BY_TRAINER, badgeRowMapper, trainerId);
    }

    public void createTrainerBadge(int trainerId, int badgeId) {
        jdbcTemplate.execute(String.format(CREATE_TRAINER_BADGE, trainerId, badgeId));
    }
}
