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
class ItemDAOTest {
    private static final String SELECT_ALL = "SELECT * from items";
    private static final String SELECT_BY_ID = "SELECT * from items WHERE id = ?";
    private static final String CREATE = "INSERT into items VALUES(%s, %s)";

    private static final int ID_1 = 20;
    private static final String ITEM_NAME_1 = "Potion";
    private static final int CHARGES_1 = 3;
    private static final String ITEM_NAME_2 = "Pokeball";
    private static final int ID_2 = 25;
    private static final int CHARGES_2 = 1;

    @Mock
    JdbcTemplate mockJdbcTemplate;
    @Mock
    ItemRowMapper mockItemRowMapper;

    @InjectMocks
    ItemDAO itemDAO;

    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        item1 = new Item(ID_1, ITEM_NAME_1, CHARGES_1);
        item2 = new Item(ID_2, ITEM_NAME_2, CHARGES_2);
    }

    @Test
    void select_all() {
        when(mockJdbcTemplate.query(eq(SELECT_ALL), eq(mockItemRowMapper))).thenReturn(Arrays.asList(item1, item2));
        List<Item> actual = itemDAO.selectAllItems();

        verify(mockJdbcTemplate).query(SELECT_ALL, mockItemRowMapper);

        assertEquals(2, actual.size());

        assertEquals(ID_1, actual.getFirst().getId());
        assertEquals(ITEM_NAME_1, actual.getFirst().getName());
        assertEquals(CHARGES_1, actual.getFirst().getCount());

        assertEquals(ID_2, actual.get(1).getId());
        assertEquals(ITEM_NAME_2, actual.get(1).getName());
        assertEquals(CHARGES_2, actual.get(1).getCount());
    }

    @Test
    void select_by_id() {
        when(mockJdbcTemplate.queryForObject(eq(SELECT_BY_ID), eq(mockItemRowMapper), eq(ID_1))).thenReturn(item1);
        Item actual = itemDAO.selectById(ID_1);

        verify(mockJdbcTemplate).queryForObject(SELECT_BY_ID, mockItemRowMapper, ID_1);

        assertEquals(ID_1, actual.getId());
        assertEquals(ITEM_NAME_1, actual.getName());
        assertEquals(CHARGES_1, actual.getCount());
    }

    @Test
    void create() {
        itemDAO.createItem(ITEM_NAME_2, CHARGES_2);

        verify(mockJdbcTemplate).execute(String.format(CREATE, ITEM_NAME_2, CHARGES_2));
    }
}