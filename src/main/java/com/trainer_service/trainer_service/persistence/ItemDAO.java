package com.trainer_service.trainer_service.persistence;

import com.trainer_service.trainer_service.objects.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDAO {
    private static final String SELECT_ALL = "SELECT * from items";
    private static final String SELECT_BY_ID = "SELECT * from items WHERE id = ?";
    private static final String CREATE = "INSERT into items VALUES(%s, %s)";

    private JdbcTemplate jdbcTemplate;
    private ItemRowMapper itemRowMapper;

    public List<Item> selectAllItems() {
        return jdbcTemplate.query(SELECT_ALL, itemRowMapper);
    }

    public Item selectById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID, itemRowMapper, id);
    }

    public void createItem(String name, int charges) {
        jdbcTemplate.execute(String.format(CREATE, name, charges));
    }
}
