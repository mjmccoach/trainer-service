package com.trainer_service.trainer_service.persistence;

import com.trainer_service.trainer_service.objects.Item;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TrainerItemDAO {

    private static final String SELECT_ALL = "SELECT * from trainer_items where trainer_id = ?";
    private static final String SELECT_ITEM_BY_ID = "SELECT * from trainer_items where trainer_id = ? AND item_id = ?";
    private static final String CREATE = "INSERT into trainer_items VALUES(%s, %s, %s)";
    private static final String UPDATE = "UPDATE trainer_items SET charges = %s WHERE trainer_id = %s AND item_id = %s";
    private static final String DELETE = "DELETE from trainer_items WHERE trainer_id = %s AND item_id = %s";

    private JdbcTemplate jdbcTemplate;
    private ItemRowMapper itemRowMapper;

    public List<Item> getAllItemsByTrainer(int trainerId) {
        return jdbcTemplate.query(SELECT_ALL, itemRowMapper, trainerId);
    }

    public Item getItemById(int trainerId, int itemId) {
        return jdbcTemplate.queryForObject(SELECT_ITEM_BY_ID, itemRowMapper, trainerId, itemId);
    }

    public void updateItemCount(int itemCount, int trainerId, int itemId) {
        jdbcTemplate.execute(String.format(UPDATE, itemCount, trainerId, itemId));
    }

    public void deleteItem(int trainerId, int itemId) {
        jdbcTemplate.execute(String.format(DELETE, trainerId, itemId));
    }

    public void createItem(int trainerId, int itemId, int itemCount) {
        jdbcTemplate.execute(String.format(CREATE, trainerId, itemId, itemCount));
    }
}
