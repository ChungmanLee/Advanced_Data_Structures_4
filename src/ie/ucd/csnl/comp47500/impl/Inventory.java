package ie.ucd.csnl.comp47500.impl;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

import ie.ucd.csnl.comp47500.api.Entry;

/**
 * The Inventory class manages a collection of products using a hash map implementation.
 * It provides functionality to add, retrieve, update, and remove products.
 * This class implements Iterable to allow iterating over all products in the inventory.
 */

public class Inventory implements Iterable<Entry<String, Product>> {
    // Hash map to store products, keyed by product ID
    private HashMapImpl<String, Product> products;
    /**
     * Retrieves all the keys from the inventory.
     * @return A set of all the keys in the inventory.
     */
    public List<String> getKeys() {
        return products.getAllKeys();
    }

    /**
     * Checks if the inventory is empty.
     *
     * @return true if the inventory is empty, false otherwise.
     */
    public boolean isEmpty() {
        return products.isEmpty();
    }
    

    /**
     * Constructor to initialize the inventory with an empty hash map.
     */
    public Inventory() {
        this.products = new HashMapImpl<>();
    }

    /**
     * Returns the number of products in the inventory.
     * @return The size of the inventory.
     */
    public int size() {
        return products.size();
    }
    
    /**
     * Adds a product to the inventory.
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    /**
     * Retrieves a product by its ID.
     * @param productId The ID of the product to retrieve.
     * @return The product if found, otherwise returns null.
     */
    public Product getProduct(String productId) {
        return products.get(productId);
    }

    /**
     * Updates the stock quantity of a product.
     * @param productId The ID of the product to update.
     * @param newStockQuantity The new stock quantity to be set.
     */
    public void updateStock(String productId, int newStockQuantity) {
        Product product = getProduct(productId);
        if (product != null) {
            product.setStockQuantity(newStockQuantity);
        }
    }

    /**
     * Removes a product from the inventory by its ID.
     * @param productId The ID of the product to be removed.
     */
    public void removeProduct(String productId) {
    	try {
    		products.remove(productId);
		} catch (Exception e) {
			
		}
        
    }

    /**
     * Displays information about all products in the inventory.
     * Iterates through all products and prints their details.
     */
    public void displayAllProducts() {
        for (Entry<String, Product> entry : this) {
            entry.getValue().displayProductInfo();
            System.out.println("----------------------------------");
        }
    }

    /**
     * Provides an iterator for the inventory to allow iteration over all products.
     * @return an iterator over the entries in the product map.
     */
    @Override
    public Iterator<Entry<String, Product>> iterator() {
        return products.iterator(); // Returns the iterator provided by HashMapImpl
    }
}

