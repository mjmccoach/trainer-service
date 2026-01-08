package com.trainer_service.trainer_service.rest;

import com.trainer_service.trainer_service.objects.Item;
import com.trainer_service.trainer_service.objects.payload.ItemPayload;
import com.trainer_service.trainer_service.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@AllArgsConstructor
public class ItemResource {
    private ItemService itemService;

    @GetMapping("/all")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable("id") int id) {
        return itemService.getItemById(id);
    }

    @PostMapping
    public void createItem(@RequestBody ItemPayload payload) {
        itemService.createItem(payload.getName(), payload.getBaseCharges());
    }
}
