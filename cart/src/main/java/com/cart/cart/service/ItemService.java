package com.cart.cart.service;

import com.cart.cart.exception.ItemNotFoundException;
import com.cart.cart.model.Item;
import com.cart.cart.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item item) {
        Item existingItem = itemRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(id));

        existingItem.setName(item.getName());
        existingItem.setPrice(item.getPrice());
        existingItem.setQuantity(item.getQuantity());
        return itemRepository.save(existingItem);
    }

    public Item patchItem(Long id, Map<String, Object> updates) {
        Item existingItem = itemRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existingItem.setName((String) value);
                    break;
                case "price":
                    existingItem.setPrice((Double) value);
                    break;
                case "quantity":
                    existingItem.setQuantity((Integer) value);
                    break;
            }
        });

        return itemRepository.save(existingItem);
    }

    public boolean deleteItem(Long id) {
        Item existingItem = itemRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(id));

        itemRepository.deleteById(id);
        return true;
    }
}
