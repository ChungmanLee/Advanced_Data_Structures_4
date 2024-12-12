package ie.ucd.csnl.comp47500.impl;

public class Product {
	/**
     * productId Unique identifier for the product.
     * name Name of the product.
     * description Description of the product.
     * price Price of the product.
     * stockQuantity Initial stock quantity of the product.
     * category Category of the product.
     * salesQuantity Sales quantity of the product.
     */
	
    private String productId;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private int salesQuantity = 0;

    // Constructor
    public Product(String productId, String name, String description, double price, int stockQuantity, String category) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }
    
    // decrease stock quantity
    public void addSales(int quantity) {
        this.salesQuantity += quantity;
        this.stockQuantity -= quantity;
    }

    // Getter methods
    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getCategory() {
        return category;
    }
    
    public int getSalesQuantity() {
        return this.salesQuantity;
    }

    // Setter methods
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Method to display product information
    public void displayProductInfo() {
        // System.out.println("Product ID: " + productId);
        // System.out.println("Name: " + name);
        // System.out.println("Description: " + description);
        // System.out.println("Price: $" + price);
        // System.out.println("Stock Quantity: " + stockQuantity);
        // System.out.println("Category: " + category);
        // System.out.println("Sales Quantity: " + salesQuantity);
        System.out.println("Product ID: " + productId + ", Name: " + name + ", Description: " + description + ", Price: $" + price + ", Stock Quantity: " + stockQuantity + ", Category: " + category + ", Sales Quantity: " + salesQuantity);
    }
}
