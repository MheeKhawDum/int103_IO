package org.example;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> itemList;

    public Inventory() {
        this.itemList = new ArrayList<>();
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void removeItem(String itemId) {
        itemList.removeIf(item -> item.getItemId().equals(itemId));
    }

    public Item getItem(String itemId) {
        for (Item item : itemList) {
            if (item.getItemId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }

    public void updateItemQuantity(String itemId, Integer quantity) {
        Item item = getItem(itemId);
        if (item != null) {
            item.setItemQuantity(quantity);
        }
    }

    public void updateItemPrice(String itemId, Double price) {
        Item item = getItem(itemId);
        if (item != null) {
            item.setItemPrice(price);
        }
    }

    public List<Item> listItems() {
        return itemList;
    }
}

