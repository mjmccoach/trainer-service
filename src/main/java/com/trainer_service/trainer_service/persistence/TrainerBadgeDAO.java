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
    private JdbcTemplate jdbcTemplate;
    private BadgeRowMapper badgeRowMapper;

    //Get all badges by trainer id
    //Add badge to trainer
    public List<GymBadge> getAllBadgesByTrainerId(int trainerId) {
        return jdbcTemplate.query(SELECT_ALL_BY_TRAINER, badgeRowMapper, trainerId);
    }
}
