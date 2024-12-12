package ie.ucd.csnl.comp47500.impl;

import ie.ucd.csnl.comp47500.api.Entry;
import ie.ucd.csnl.comp47500.api.Map;

/**
 * An abstract class that provides a skeletal implementation of the Map interface.
 * This class provides common functionality and default implementations for various methods.
 * Subclasses of AbstractMap must implement the abstract methods to provide concrete
 * implementations of the Map interface.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public abstract class AbstractMap<K, V> implements Map<K,V> {

    protected int size; // the number of key-value mappings in the map
    private int capacity=16; // the initial capacity of the map
    public static final double DEFAULT_LOAD_FACTOR = 0.5; // Default load factor
	private static final int A = 314159265;		//scale factor
	private static final int B = 271828182;		//shift
    
    /**
     * Constructs an empty AbstractMap with the default initial capacity.
     */
    protected AbstractMap() {}
    
    /**
     * Constructs an empty AbstractMap with the specified initial capacity.
     *
     * @param c the initial capacity of the map
     */
    protected AbstractMap(int c) {
    	this.capacity=c;
    }
    
    /**
     * Calculates the hash value for a given key.
     *
     * @param key the key for which to calculate the hash value
     * @return the hash value of the key
     */
    protected int hash(K key) {return key.hashCode();}
    
    /**
     * Returns the capacity of the map.
     *
     * @return the capacity of the map
     */
    public int getCapacity() {
		return capacity;
	}
    
    /**
     * Sets the capacity of the map.
     *
     * @param capacity the new capacity of the map
     */
    protected void setCapacity(int capacity) {
    	this.capacity= capacity;
    }
    
    @Override
    public V put(K key, V value) {
    	int index=generateHash(key);
		return bucketPut(index,key,value);
    }
    
    @Override
    public V get(K key) {
    	int index= generateHash(key);
    	return bucketGet(index, key);
    }
    
    @Override
    public V remove(K key) {
    	int index = generateHash(key);
    	return bucketRemove(index, key);
    }
    
    /**
     * Generates a hash value for the given key using the hash function.
     *
     * @param key the key for which to generate the hash value
     * @return the generated hash value
     */
    protected int generateHash(K key) {
    	return Math.floorMod((A * key.hashCode()) + B, this.getCapacity());
    }
    
    /**
     * Inserts the specified key-value pair into the map at the specified index.
     * This method is called by the put() method.
     *
     * @param index the index at which to insert the key-value pair
     * @param key the key to be inserted
     * @param value the value to be inserted
     * @return the previous value associated with the key, or null if there was no mapping for the key
     */
    protected abstract V bucketPut(int index, K key, V value);
    
    /**
     * Retrieves the value associated with the specified key from the map at the specified index.
     * This method is called by the get() method.
     *
     * @param index the index from which to retrieve the value
     * @param key the key whose associated value is to be retrieved
     * @return the value associated with the key, or null if there is no mapping for the key
     */
    protected abstract V bucketGet(int index, K key);
    
    /**
     * Removes the key-value pair associated with the specified key from the map at the specified index.
     * This method is called by the remove() method.
     *
     * @param index the index from which to remove the key-value pair
     * @param key the key whose associated value is to be removed
     * @return the value associated with the key, or null if there was no mapping for the key
     */
    protected abstract V bucketRemove(int index, K key);
    
    /**
     * Resizes the map when the load factor exceeds a threshold.
     * This method is called internally when necessary.
     */
    protected abstract void resize();
}
