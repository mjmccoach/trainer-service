package com.trainer_service.trainer_service.persistence;

import com.trainer_service.trainer_service.objects.Trainer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

@Component
public class TrainerRowMapper implements RowMapper<Trainer> {
    @Override
    public Trainer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Trainer(
                rs.getInt("id"),
                rs.getString("name"),
                Collections.emptyList()
                //Badges will be populated from a different query to normalise data
        );
    }
}
