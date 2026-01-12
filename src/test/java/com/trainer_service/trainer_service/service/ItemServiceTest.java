package com.trainer_service.trainer_service.service;

import com.trainer_service.trainer_service.objects.Item;
import com.trainer_service.trainer_service.persistence.ItemDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ItemServiceTest {
    private static final int ID_1 = 20;
    private static final String ITEM_NAME_1 = "Potion";
    private static final int CHARGES_1 = 3;
    private static final String ITEM_NAME_2 = "Pokeball";
    private static final int ID_2 = 25;
    private static final int CHARGES_2 = 1;

    @Mock
    ItemDAO mockItemDAO;

    @InjectMocks
    ItemService itemService;

    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {
        item1 = new Item(ID_1, ITEM_NAME_1, CHARGES_1);
        item2 = new Item(ID_2, ITEM_NAME_2, CHARGES_2);
    }

    @Test
    void get_all_items() {
        when(mockItemDAO.selectAllItems()).thenReturn(Arrays.asList(item1, item2));
        List<Item> actual = itemService.getAllItems();

        assertEquals(2, actual.size());

        assertEquals(ID_1, actual.getFirst().getId());
        assertEquals(ITEM_NAME_1, actual.getFirst().getName());
        assertEquals(CHARGES_1, actual.getFirst().getCount());

        assertEquals(ID_2, actual.get(1).getId());
        assertEquals(ITEM_NAME_2, actual.get(1).getName());
        assertEquals(CHARGES_2, actual.get(1).getCount());
    }

    @Test
    void get_by_id() {
        when(mockItemDAO.selectById(eq(ID_1))).thenReturn(item1);
        Item actual = itemService.getItemById(ID_1);

        assertEquals(ID_1, actual.getId());
        assertEquals(ITEM_NAME_1, actual.getName());
        assertEquals(CHARGES_1, actual.getCount());
    }

    @Test
    void create_item() {
        itemService.createItem(ITEM_NAME_2, CHARGES_2);

        verify(mockItemDAO).createItem(ITEM_NAME_2, CHARGES_2);
    }
}