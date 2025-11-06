package com.trainer_service.trainer_service.persistence;

import com.trainer_service.trainer_service.objects.Trainer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainerDao {

    private static final String SELECT = "SELECT * from `Trainer` WHERE id = ?;";
    private static final String SELECT_ALL = "SELECT * from `Trainer`;";
    private static final String UPDATE = "UPDATE `Trainer` " +
            "                             SET name = '%s' " +
            "                             WHERE id = %s;";

    private static final String DEACTIVATE = "UPDATE `Trainer` " +
            "SET active = 0 " +
            "WHERE id = %s ";
    private final JdbcTemplate jdbcTemplate;
    private final TrainerRowMapper trainerRowMapper;

    public TrainerDao(JdbcTemplate jdbcTemplate, TrainerRowMapper trainerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.trainerRowMapper = trainerRowMapper;
    }

    public Trainer getTrainer(int id) {
        return jdbcTemplate.queryForObject(SELECT, trainerRowMapper, id);
    }

    public List<Trainer> getAllTrainers() {
        return jdbcTemplate.query(SELECT_ALL, trainerRowMapper);
    }

    public void updateTrainer(String name, int id) {
        jdbcTemplate.execute(String.format(UPDATE, name, id));
    }

    public void deactivateTrainer(int id) {
        jdbcTemplate.execute(String.format(DEACTIVATE, id));
    }
}
