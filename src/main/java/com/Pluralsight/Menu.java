package com.Pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    static Map<String, Menu> Order = new HashMap<>();

    public static void main(String[] args) {
        loadOrder("src/main/java/com/Pluralsight/Order");

        // View all menu items
        System.out.println("All Menu:");
        for (Menu menu : Order.values()) {
            System.out.println(menu);
        }

        // Search by Menu Item Name
        System.out.println("\nSearch by Menu Item Name:");
        searchByName("Mini Projector");

        // Search by Price
        System.out.println("\nSearch by Price:");
        searchByPrice(89.95);

        // Search by Category
        System.out.println("\nSearch by Category:");
        searchByCategory("Audio Video");
    }

    static void loadOrder(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String sku = parts[0];
                    String menuName = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String category = parts[3];
                    Menu menu = new Menu(sku, menuName, price, category);
                    Order.put(sku, menu);
                } else {
                    System.out.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void searchByName(String menuName) {
        for (Menu menu : Order.values()) {
            if (menu.getMenuItemName().equalsIgnoreCase(menuName)) {
                System.out.println(menu);
            }
        }
    }

    static void searchByPrice(double price) {
        for (Menu menu : Order.values()) {
            if (menu.getPrice() == price) {
                System.out.println(menu);
            }
        }
    }

    static void searchByCategory(String category) {
        for (Menu menu : Order.values()) {
            if (menu.getCategory().equalsIgnoreCase(category)) {
                System.out.println(menu);
            }
        }
    }

    private String SKU;
    private String menuItemName;
    private double price;
    private String category;

    public Menu(String SKU, String menuItemName, double price, String category) {
        this.SKU = SKU;
        this.menuItemName = menuItemName;
        this.price = price;
        this.category = category;
    }

    public String getSKU() {
        return SKU;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "SKU: " + SKU + ", Menu Item Name: " + menuItemName + ", Price: $" + price + ", Category: " + category;
    }
}
