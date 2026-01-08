package com.trainer_service.trainer_service.service;

import com.trainer_service.trainer_service.objects.Item;
import com.trainer_service.trainer_service.persistence.ItemDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ItemService {
    private ItemDAO itemDAO;

    public List<Item> getAllItems() {
        return itemDAO.selectAllItems();
    }

    public Item getItemById(int id) {
        return itemDAO.selectById(id);
    }

    public void createItem(String name, int baseCharges) {
        itemDAO.createItem(name, baseCharges);
    }
}
