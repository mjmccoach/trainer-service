package com.trainer_service.trainer_service.persistence;

import com.trainer_service.trainer_service.objects.GymBadge;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class BadgeDAO {

    private static final String SELECT_BY_ID = "SELECT * FROM gym_badges WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM gym_badges";
    private static final String CREATE = "INSERT into gym_badges VALUES(%s)";
    private static final String DELETE = "DELETE from gym_badges WHERE id = %s";

    private JdbcTemplate jdbcTemplate;
    private BadgeRowMapper badgeRowMapper;

    public GymBadge getBadgeById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, badgeRowMapper, id);
    }

    public List<GymBadge> getAllGymBadges() {
        return jdbcTemplate.query(SELECT_ALL, badgeRowMapper);
    }

    public void createGymBadge(String name) {
        jdbcTemplate.execute(String.format(CREATE, name));
    }

    public void deleteGymBadge(int id) {
        jdbcTemplate.execute(String.format(DELETE, id));
    }
}
