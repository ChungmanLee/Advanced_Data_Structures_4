package ie.ucd.csnl.comp47500.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ie.ucd.csnl.comp47500.InventoryManager;
import ie.ucd.csnl.comp47500.impl.Inventory;

class InventoryManagerTest {

    private Inventory inventory;
    private InventoryManager manager;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
        manager = new InventoryManager(inventory);
    }

    @Test
    void testAddNewProduct() {
        manager.addNewProduct("P001", "Laptop", "High performance laptop", 1200.00, 10, "Electronics");
        assertEquals(1, inventory.size());
    }

    @Test
    void testDisplayProductDetails() {
        manager.addNewProduct("P001", "Laptop", "High performance laptop", 1200.00, 10, "Electronics");
        assertDoesNotThrow(() -> manager.displayProductDetails("P001"));
    }

    @Test
    void testAdjustPrices() {
        manager.addNewProduct("P001", "Laptop", "High performance laptop", 1000.00, 5, "Electronics");
        manager.addNewProduct("P002", "Smartphone", "Latest model smartphone", 1000.00, 55, "Electronics");
        manager.adjustPrices();
        assertEquals(1100.00, inventory.getProduct("P001").getPrice());
        assertEquals(900.00, inventory.getProduct("P002").getPrice());
    }

    @Test
    void testUpdateProductStock() {
        manager.addNewProduct("P001", "Laptop", "High performance laptop", 1200.00, 10, "Electronics");
        manager.updateProductStock("P001", 15);
        assertEquals(15, inventory.getProduct("P001").getStockQuantity());
    }

    @Test
    void testRemoveProduct() {
        manager.addNewProduct("P001", "Laptop", "High performance laptop", 1200.00, 10, "Electronics");
        manager.removeProduct("P001");
        assertNull(inventory.getProduct("P001"));
    }

    @Test
    void testProcessOrder() {
        manager.addNewProduct("P001", "Laptop", "High performance laptop", 1200.00, 10, "Electronics");
        String result = manager.processOrder("P001", 2);
        assertEquals("Order completed for: P001, 2 units.", result);
        assertEquals(8, inventory.getProduct("P001").getStockQuantity());
    }

    @Test
    void testProcessOrderNotEnoughStock() {
        manager.addNewProduct("P001", "Laptop", "High performance laptop", 1200.00, 10, "Electronics");
        String result = manager.processOrder("P001", 20);
        assertTrue(result.contains("Insufficient stock for: P001. Only 10 units available."));
    }

    @Test
    void testInventoryIsEmpty() {
        assertTrue(manager.isEmpty());
    }

    @Test
    void testSize() {
        manager.addNewProduct("P001", "Laptop", "High performance laptop", 1200.00, 10, "Electronics");
        assertEquals(1, manager.size());
    }
}