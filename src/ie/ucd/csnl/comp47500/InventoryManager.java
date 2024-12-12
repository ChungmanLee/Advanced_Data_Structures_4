package ie.ucd.csnl.comp47500;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ie.ucd.csnl.comp47500.api.Entry;
import ie.ucd.csnl.comp47500.constant.Message;
import ie.ucd.csnl.comp47500.impl.Inventory;
import ie.ucd.csnl.comp47500.impl.Product;

/**
 * The InventoryManager class provides methods to manage the inventory,
 * including adding new products, displaying product details, adjusting prices,
 * updating stock quantities, and removing products.
 */

public class InventoryManager {
	// TODO: Create sample datasets and apply

	private Inventory inventory;

	/**
	 * Constructs an InventoryManager with a reference to an existing Inventory.
	 * 
	 * @param inventory The inventory this manager will operate on.
	 */

	public InventoryManager(Inventory inventory) {
		this.inventory = inventory;
	}
	/**
	 * Retrieves all the keys from the inventory.
	 * 
	 * @return A set containing all the keys in the inventory.
	 */
	public List<String> getKeys() {
		return inventory.getKeys();
	}

	// Add a new product to the inventory.
	public void addNewProduct(String productId, String name, String description, double price, int stockQuantity,
			String category) {
		Product newProduct = new Product(productId, name, description, price, stockQuantity, category);
		inventory.addProduct(newProduct);
	}

	/**
	 * Displays details of a specific product by its ID.
	 * 
	 * @param productId The ID of the product to display.
	 */
	public void displayProductDetails(String productId) {
		Product product = inventory.getProduct(productId);
		if (product != null) {
			product.displayProductInfo();
		} else {
			System.out.println("Product with ID " + productId + " not found.");
		}
	}

	/**
	 * Adjusts the prices of all products in the inventory based on their stock
	 * quantities. Increases the price by 10% if the stock is less than 10 units,
	 * and decreases by 10% if over 50 units.
	 */
	public void adjustPrices() {
		for (Entry<String, Product> entry : inventory) {
			Product product = entry.getValue();
			if (product.getStockQuantity() < 10) {
				product.setPrice(product.getPrice() * 1.1);
			} else if (product.getStockQuantity() > 50) {
				product.setPrice(product.getPrice() * 0.9);
			}
		}
		System.out.println("Prices adjusted based on stock levels.");
	}

	/**
	 * Checks if the inventory is empty.
	 * 
	 * @return True if the inventory is empty, otherwise false.
	 */
	public boolean isEmpty() {
		return inventory.isEmpty();
	}
	/**
	 * Updates the stock quantity of a product by its ID.
	 * 
	 * @param productId        The ID of the product to update.
	 * @param newStockQuantity The new stock quantity to set.
	 */
	public void updateProductStock(String productId, int newStockQuantity) {
		inventory.updateStock(productId, newStockQuantity);
	}

	/**
	 * Removes a product from the inventory by its ID.
	 * 
	 * @param productId The ID of the product to remove.
	 */
	public void removeProduct(String productId) {
		inventory.removeProduct(productId);
	}

	// Displays all products in the inventory.
	public void displayInventory() {
		inventory.displayAllProducts();
	}

    /**
     * Returns the size of the inventory.
     * 
     * @return The size of the inventory.
     */
	public int size() {
		return inventory.size();
	}

	/**
	 * Processes an order for a specified quantity of a product identified by its
	 * product ID. Adjusts the stock quantity accordingly if enough stock is
	 * available.
	 * 
	 * @param productId The ID of the product to order.
	 * @param quantity  The quantity of the product to order.
	 * @return A message indicating the result of the order attempt.
	 */
	public String processOrder(String productId, int quantity) {
		Product product = inventory.getProduct(productId);
		if (product != null) {
			int currentStock = product.getStockQuantity();
			if (currentStock >= quantity) {
				// Sufficient stock available, process the sale
				// product.setStockQuantity(currentStock - quantity); // Decrease the stock by
				// the ordered quantity
				product.addSales(quantity); // Record each sale
				return "Order completed for: " + productId + ", " + quantity + " units.";
			} else {
				// Not enough stock
				return "Insufficient stock for: " + productId + ". Only " + currentStock + " units available.";
			}
		} else {
			return "Product not found: " + productId;
		}
	}

	/**
	 * Reads data from a CSV file and adds new products to the inventory.
	 * 
	 * @param filePath The path of the CSV file.
	 */
	public static List<String> readInventoryDataFromCSV(String filePath, InventoryManager manager) {
		List<String> productIds = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(";");
				String productId = data[0].replaceAll("'", "");
				productIds.add(productId);
				String name = data[1].replaceAll("'", "");
				String description = data[2].replaceAll("'", "");
				double price = Double.parseDouble(data[3].replaceAll("'", ""));
				int stockQuantity = Integer.parseInt(data[4].replaceAll("'", ""));
				String category = data[5].replaceAll("'", "");
				manager.addNewProduct(productId, name, description, price, stockQuantity, category);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return productIds;
	}


    private static void basicFuntionchecks() {

		System.out.println("***********implemtation of basic hashmap functions***********");
	
        Inventory inventory = new Inventory();
        InventoryManager manager = new InventoryManager(inventory);
        readInventoryDataFromCSV(Message.RESOURCE_PATH + "inventory_data/" + "inventory_10.csv", manager);
        System.out.println("Inventory Size" + manager.size()+"\n");
        //Display all products
        System.out.println("initial Inventory:");
        manager.displayInventory();

        //Adding some products
        manager.addNewProduct("P001", "Laptop", "High performance laptop", 1200.00,
        10, "Electronics");
        manager.addNewProduct("P002", "Smartphone", "Latest model smartphone",
        800.00, 20, "Electronics");

        manager.removeProduct("P001");

        System.out.println("\nUpdating stock for  Smartphone");
		manager.updateProductStock("P002", 5); // Decrease stock of laptops

        manager.displayProductDetails("P002");

		//List all keys
		System.out.println("All keys in the inventory:\n");
		List<String> keys = manager.getKeys();
		for (String key : keys) {
			System.out.println(key+"\n");
		}
			

        //Display all products
        System.out.println("final Inventory:");
        manager.displayInventory();

    }
	// Main method to run the InventoryManager.
	public static void main(String[] args) {

		String[] inventoryFiles = { "inventory_10.csv", "inventory_100.csv", "inventory_1000.csv",
				"inventory_10000.csv", "inventory_100000.csv" };

        // testing basic hashmap functions
        basicFuntionchecks();
		// Create an instance of Inventory
		System.out.println("***********Time Complexity checks***********\n");
		for (int i = 0; i < inventoryFiles.length; i++) {
			Inventory inventory = new Inventory();

			// Create an instance of InventoryManager
			InventoryManager manager = new InventoryManager(inventory);
			long startTimeToAddData = System.currentTimeMillis();
			readInventoryDataFromCSV(Message.RESOURCE_PATH + "inventory_data/" + inventoryFiles[i], manager);
			long startTimeTocheckSize = System.currentTimeMillis();
			System.out.println("Inventory of size ----->" + manager.size()+"\n");
			long endTimeTocheckSize = System.currentTimeMillis();
			long differenceTocheckSize = endTimeTocheckSize - startTimeTocheckSize;
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^Time taken to check size: " + (differenceTocheckSize));
			long endTimeToAddData = System.currentTimeMillis();
			long differenceToAddData = endTimeToAddData - startTimeToAddData;
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^Time taken to add data: " + (differenceToAddData));
			
			long startTimeTocheckIsEmpty = System.currentTimeMillis();
			System.out.println("is the inventory empty?" + manager.isEmpty());
			long endTimeTocheckIsEmpty = System.currentTimeMillis();
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^Time taken to check isEmpty: " + (endTimeTocheckIsEmpty - startTimeTocheckIsEmpty) + " ms.");


			// Display details of a specific product
			long startTimeforRU = System.currentTimeMillis();
			System.out.println("\nDisplaying details of Concrete gloves:");
			manager.displayProductDetails("2605d30f-32a0-4d1a-9327-598ee1577d85");

			// Update stock quantity for a product
			System.out.println("\nUpdating stock for  Concrete gloves:");
			manager.updateProductStock("2605d30f-32a0-4d1a-9327-598ee1577d85", 5); // Decrease stock of laptops
			// manager.displayProductDetails("2605d30f-32a0-4d1a-9327-598ee1577d85");

			// Process a few orders with different scenarios
			System.out.println("\nProcessing orders:");
			String orderResult1 = manager.processOrder("fb7de8d1-9ba8-426d-9736-abe61b886ddb", 2); // Order 2 laptops
			System.out.println(orderResult1);

			String orderResult2 = manager.processOrder("93d032e7-349b-49c1-83ce-9e92a38511b5", 25); // Try to order 25
																									// smart phones
			System.out.println(orderResult2);

			String orderResult3 = manager.processOrder("eda869d8-33d5-4184-a4e9-38ce2012b93d", 15); // Order 15 coffee
																									// makers
			System.out.println(orderResult3);

			System.out.println("inventory size" + manager.size());
			long endTimeforRU = System.currentTimeMillis();
			long differnceforRU = endTimeforRU - startTimeforRU;
			System.out
					.println("^^^^^^^^^^^^^^^^^^^^^^^^Time taken for get and put operations of items in inventory: "
							+ (differnceforRU));
			// Adjust prices based on stock levels
			System.out.println("\nAdjusting prices based on stock levels:");
			manager.adjustPrices();
			// manager.displayInventory();

			// Remove a product
			long startTimeforRemove = System.currentTimeMillis();
			System.out.println("\nRemoving awesome fresh plants from inventory:");
			manager.removeProduct("fb7de8d1-9ba8-426d-9736-abe61b886ddb");
			// manager.displayInventory();

			// Final display of all products
			System.out.println("\nFinal Inventory:");
			// manager.displayInventory();
			System.out.println("inventory size" + manager.size());
			long endTimeforRemove = System.currentTimeMillis();
			long differnceforRemove = endTimeforRemove - startTimeforRemove;
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^Time taken for remove operation of item in inventory: "
					+ (differnceforRemove));
			System.out.println("________________________________________________________________________________");
		}

		processingOrder();

	}

	private static void processingOrder() {
		Inventory inventory = new Inventory();
		System.out.println("Keeping Inventory size as 10000 and changing order size");
		// Create an instance of InventoryManager
		InventoryManager manager = new InventoryManager(inventory);
		// long startTimeToAddData = System.currentTimeMillis();
		List<String> productIds = readInventoryDataFromCSV(Message.RESOURCE_PATH + "inventory_data/inventory_10000.csv",
				manager);

		Integer[] orders = { 10, 100, 1000 };
		Random random = new Random();
		Integer inventorySize = manager.inventory.size();
		for (Integer order : orders) {

			// Generating random unique order numbers to process the order.
			Set<Integer> generatedNumbers = new HashSet<>();
			while (generatedNumbers.size() < order) {
				int randomNumber = random.nextInt(inventorySize-1 - 0 + 1) + 0;
				if (generatedNumbers.isEmpty()) {
					generatedNumbers.add(randomNumber);
				} else if (!generatedNumbers.contains(randomNumber)) {
					generatedNumbers.add(randomNumber);
				}
			}
			List<Integer> randomNumbers = new ArrayList<>(generatedNumbers);

			System.out.println("Processing " + randomNumbers.size() + " orders from " + inventorySize + " inventory");
			long startTimeToProcessOrder = System.currentTimeMillis();
			for (Integer randomNumber : randomNumbers) {
				int stockNumber = random.nextInt(5 - 0) + 0;
				// System.out.println(manager.processOrder(productIds.get(randomNumber),
				// stockNumber));
				manager.processOrder(productIds.get(randomNumber), stockNumber);
			}
			long endTimeToProcessOrder = System.currentTimeMillis();
			System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^Time taken to process " + randomNumbers.size() + " orders: "
					+ (endTimeToProcessOrder - startTimeToProcessOrder) + " ms.");

			//
			System.out.println("Updating " + randomNumbers.size() + " orders from " + inventorySize + " inventory");
			long startTimeToUpdateOrder = System.currentTimeMillis();
			for (Integer randomNumber : randomNumbers) {
				int stockNumber = random.nextInt(5 - 0) + 0;

				manager.updateProductStock(productIds.get(randomNumber), stockNumber);
			}
			long endTimeToUpdateOrder = System.currentTimeMillis();
			System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^Time taken to update " + randomNumbers.size() + " orders: "
					+ (endTimeToUpdateOrder - startTimeToUpdateOrder) + " ms.");

			long startTimeTocheckIsEmpty = System.currentTimeMillis();
			System.out.println("is the inventory empty?" + manager.isEmpty());
			long endTimeTocheckIsEmpty = System.currentTimeMillis();
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^Time taken to check isEmpty: " + (endTimeTocheckIsEmpty - startTimeTocheckIsEmpty) + " ms.");


			System.out.println("Removing " + randomNumbers.size() + " orders from " + inventorySize + " inventory");
			long startTimeforRemoveOrder = System.currentTimeMillis();
			for (Integer randomNumber : randomNumbers) {
				manager.removeProduct(productIds.get(randomNumber));
			}
			long endTimeforRemoveOrder = System.currentTimeMillis();
			System.out.println("\n^^^^^^^^^^^^^^^^^^^^^^^^Time taken to remove " + randomNumbers.size() + " orders: "
					+ (endTimeforRemoveOrder - startTimeforRemoveOrder) + " ms.");

			System.out.println("________________________________________________________________________________");
		}


	}
}
