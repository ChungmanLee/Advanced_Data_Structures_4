package ie.ucd.csnl.comp47500.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ie.ucd.csnl.comp47500.impl.Inventory;
import ie.ucd.csnl.comp47500.impl.Product;
import ie.ucd.csnl.comp47500.api.Entry;

class InventoryTest {

    private Inventory inventory;

    @BeforeEach
    void setUp() {
        inventory = new Inventory();
    }

    @Test
    void testAddProduct() {
        Product product = new Product("P001", "Laptop", "High performance laptop", 1200.00, 10, "Electronics");
        inventory.addProduct(product);
        assertFalse(inventory.isEmpty());
        assertEquals(1, inventory.size());
    }

    @Test
    void testGetProduct() {
        Product product = new Product("P002", "Smartphone", "Latest model smartphone", 800.00, 20, "Electronics");
        inventory.addProduct(product);
        Product retrievedProduct = inventory.getProduct("P002");
        assertNotNull(retrievedProduct);
        assertEquals("Smartphone", retrievedProduct.getName());
    }

    @Test
    void testUpdateStock() {
        Product product = new Product("P003", "Coffee Maker", "Brews coffee in minutes", 99.99, 30, "Appliances");
        inventory.addProduct(product);
        inventory.updateStock("P003", 40);
        assertEquals(40, inventory.getProduct("P003").getStockQuantity());
    }

    @Test
    void testRemoveProduct() {
        Product product = new Product("P004", "Printer", "High quality printer", 299.99, 15, "Office");
        inventory.addProduct(product);
        inventory.removeProduct("P004");
        assertNull(inventory.getProduct("P004"));
    }

    @Test
    void testIsEmpty() {
        assertTrue(inventory.isEmpty());
        inventory.addProduct(new Product("P005", "Desk Lamp", "Bright desk lamp", 45.99, 5, "Office"));
        assertFalse(inventory.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, inventory.size());
        inventory.addProduct(new Product("P006", "Monitor", "4K Ultra HD monitor", 350.00, 10, "Electronics"));
        inventory.addProduct(new Product("P007", "Keyboard", "Mechanical keyboard", 120.00, 10, "Electronics"));
        assertEquals(2, inventory.size());
    }

    @Test
    void testIterator() {
        inventory.addProduct(new Product("P008", "Mouse", "Wireless mouse", 25.99, 20, "Electronics"));
        inventory.addProduct(new Product("P009", "Charger", "Fast charging adapter", 19.99, 15, "Electronics"));
        int count = 0;
        for (Entry<String, Product> entry : inventory) {
            assertNotNull(entry.getValue());
            count++;
        }
        assertEquals(2, count);
    }
}
