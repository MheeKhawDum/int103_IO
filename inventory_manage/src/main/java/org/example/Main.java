package org.example;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager("inventory.dat");

        manager.addItem("1", "Apple", 100, 0.99);
        manager.addItem("2", "Banana", 150, 0.59);
        manager.addItem("3", "Orange", 200, 0.79);

        System.out.println("Listing items:");
        for (Item item : manager.listItems()) {
            System.out.println(item.getItemId() + ": " + item.getItemName() + ", Quantity: " + item.getItemQuantity() + ", Price: " + item.getItemPrice());
        }

        manager.updateItemQuantity("1", 120);
        manager.updateItemPrice("2", 0.69);

        System.out.println("\nAfter updates:");
        for (Item item : manager.listItems()) {
            System.out.println(item.getItemId() + ": " + item.getItemName() + ", Quantity: " + item.getItemQuantity() + ", Price: " + item.getItemPrice());
        }

        manager.removeItem("3");

        System.out.println("\nAfter removal:");
        for (Item item : manager.listItems()) {
            System.out.println(item.getItemId() + ": " + item.getItemName() + ", Quantity: " + item.getItemQuantity() + ", Price: " + item.getItemPrice());
        }
    }
}
