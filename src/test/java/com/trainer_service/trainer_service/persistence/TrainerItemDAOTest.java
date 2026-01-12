package com.trainer_service.trainer_service.persistence;

import com.trainer_service.trainer_service.objects.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TrainerItemDAOTest {
    private static final String SELECT_ALL = "SELECT * from trainer_items where trainer_id = ?";
    private static final String SELECT_ITEM_BY_ID = "SELECT * from trainer_items where trainer_id = ? AND item_id = ?";
    private static final String CREATE = "INSERT into trainer_items VALUES(%s, %s, %s)";
    private static final String UPDATE = "UPDATE trainer_items SET charges = %s WHERE trainer_id = %s AND item_id = %s";
    private static final String DELETE = "DELETE from trainer_items WHERE trainer_id = %s AND item_id = %s";

    private static final int TRAINER_ID_1 = 25;
    private static final int ID_1 = 20;
    private static final String ITEM_NAME_1 = "Potion";
    private static final int CHARGES_1 = 3;
    private static final String ITEM_NAME_2 = "Pokeball";
    private static final int ID_2 = 25;
    private static final int CHARGES_2 = 1;

    @Mock
    JdbcTemplate mockJdbcTemplate;
    @Mock
    ItemRowMapper itemRowMapper;

    @InjectMocks
    TrainerItemDAO trainerItemDAO;

    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        item1 = new Item(ID_1, ITEM_NAME_1, CHARGES_1);
        item2 = new Item(ID_2, ITEM_NAME_2, CHARGES_2);
    }

    @Test
    void select_all_items_for_trainer() {
        when(mockJdbcTemplate.query(SELECT_ALL, itemRowMapper, TRAINER_ID_1)).thenReturn(Arrays.asList(item1, item2));

        List<Item> actual = trainerItemDAO.getAllItemsByTrainer(TRAINER_ID_1);

        assertEquals(2, actual.size());

        assertEquals(ID_1, actual.getFirst().getId());
        assertEquals(ITEM_NAME_1, actual.getFirst().getName());
        assertEquals(CHARGES_1, actual.getFirst().getCount());

        assertEquals(ID_2, actual.get(1).getId());
        assertEquals(ITEM_NAME_2, actual.get(1).getName());
        assertEquals(CHARGES_2, actual.get(1).getCount());
    }

    @Test
    void select_item_by_item_id() {
        when(mockJdbcTemplate.queryForObject(eq(SELECT_ITEM_BY_ID), eq(itemRowMapper), eq(TRAINER_ID_1), eq(ID_1))).thenReturn(item1);
        Item actual = trainerItemDAO.getItemById(TRAINER_ID_1, ID_1);

        assertEquals(ID_1, actual.getId());
        assertEquals(ITEM_NAME_1, actual.getName());
        assertEquals(CHARGES_1, actual.getCount());
    }

    @Test
    void create_trainer_item() {
        trainerItemDAO.createItem(TRAINER_ID_1, ID_1, CHARGES_1);

        verify(mockJdbcTemplate).execute(String.format(CREATE, TRAINER_ID_1, ID_1, CHARGES_1));
    }

    @Test
    void update_trainer_item() {
        trainerItemDAO.updateItemCount(CHARGES_2, TRAINER_ID_1, ID_1);

        verify(mockJdbcTemplate).execute(String.format(UPDATE, CHARGES_2, TRAINER_ID_1, ID_1));
    }

    @Test
    void delete_trainer_item() {
        trainerItemDAO.deleteItem(TRAINER_ID_1, ID_2);

        verify(mockJdbcTemplate).execute(String.format(DELETE, TRAINER_ID_1, ID_2));
    }
}