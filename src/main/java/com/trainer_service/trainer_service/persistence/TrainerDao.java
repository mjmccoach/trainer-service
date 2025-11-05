package com.trainer_service.trainer_service.persistence;

import com.trainer_service.trainer_service.objects.Trainer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDao {

    private static final String SELECT = "SELECT * from `Trainer` WHERE id = ?;";
    private final JdbcTemplate jdbcTemplate;
    private final TrainerRowMapper trainerRowMapper;

    public TrainerDao(JdbcTemplate jdbcTemplate, TrainerRowMapper trainerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.trainerRowMapper = trainerRowMapper;
    }

    public Trainer getTrainer(int id) {
        return jdbcTemplate.queryForObject(SELECT, trainerRowMapper, id);
    }
}
