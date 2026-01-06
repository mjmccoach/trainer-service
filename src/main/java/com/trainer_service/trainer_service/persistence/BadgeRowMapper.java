package com.trainer_service.trainer_service.persistence;

import com.trainer_service.trainer_service.enums.BadgeType;
import com.trainer_service.trainer_service.objects.GymBadge;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BadgeRowMapper implements RowMapper<GymBadge> {
    @Override
    public GymBadge mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GymBadge(
                rs.getInt("id"),
                BadgeType.valueOf(rs.getString("badge_name").toUpperCase()));
    }
}
