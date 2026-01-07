package com.trainer_service.trainer_service.persistence;

import com.trainer_service.trainer_service.objects.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Item(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("base_charges"));
    }
}
