package org.example;

import java.io.*;
import java.util.List;

public class InventoryManager {
    private Inventory inventory;
    private String filePath;

    public InventoryManager(String filePath) {
        this.filePath = filePath;
        this.inventory = new Inventory();
        loadInventory();
    }

    public void loadInventory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            List<Item> itemList = (List<Item>) ois.readObject();
            inventory = new Inventory();
            for (Item item : itemList) {
                inventory.addItem(item);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, starting with an empty inventory.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveInventory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(inventory.listItems());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addItem(String itemId, String itemName, Integer itemQuantity, Double itemPrice) {
        Item item = new Item(itemId, itemName, itemQuantity, itemPrice);
        inventory.addItem(item);
        saveInventory();
    }

    public void removeItem(String itemId) {
        inventory.removeItem(itemId);
        saveInventory();
    }

    public void updateItemQuantity(String itemId, Integer quantity) {
        inventory.updateItemQuantity(itemId, quantity);
        saveInventory();
    }

    public void updateItemPrice(String itemId, Double price) {
        inventory.updateItemPrice(itemId, price);
        saveInventory();
    }

    public List<Item> listItems() {
        return inventory.listItems();
    }
}
