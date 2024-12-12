package ie.ucd.csnl.comp47500.impl;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ie.ucd.csnl.comp47500.api.Entry;

/**
 * This class represents a HashMap implementation that extends the AbstractMap class.
 * It provides methods to put, get, remove, check if a key exists, and perform other operations on the map.
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values stored in the map
 */
public class HashMapImpl<K, V> extends AbstractMap<K, V> {

	private ArrayList<Entry<K, V>> map;

	/**
	 * Constructs an empty HashMap with the default capacity.
	 */
	public HashMapImpl() {
		super();
		this.map = new ArrayList<Entry<K, V>>(Collections.nCopies(this.getCapacity(), null));
	}

	/**
	 * Constructs an empty HashMap with the specified capacity.
	 *
	 * @param capacity the initial capacity of the HashMap
	 */
	public HashMapImpl(int capacity) {
		super(capacity);
		this.map = new ArrayList<Entry<K, V>>(Collections.nCopies(this.getCapacity(), null));
	}

	/**
	 * Puts the specified key-value pair into the map.
	 * If the key already exists, updates its value and returns the previous value.
	 * If the key doesn't exist, adds a new entry at the end of the chain.
	 * Resizes the map if the load factor exceeds the default load factor.
	 *
	 * @param index the index of the bucket in the map
	 * @param key   the key to be inserted or updated
	 * @param value the value to be associated with the key
	 * @return the previous value associated with the key, or null if the key didn't exist
	 */
	@Override
	protected V bucketPut(int index, K key, V value) {
		Entry<K, V> current = map.get(index);
		if (current == null)
			map.set(index, new MapEntry<K, V>(key, value));
		else {

			while (current != null) {
				if (current.getKey().equals(key)) {
					// If the key already exists, update its value
					V oldValue = current.getValue();
					current.setValue(value);
					return oldValue; // Return the previous value
				}
				current = current.getNext();
			}
			// If the key doesn't exist in the chain, add a new entry at the end
			current = map.get(index); // Reset current to the head of the chain
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(new MapEntry<>(key, value));
		}
		this.size++;
		if (this.size / this.getCapacity() >= DEFAULT_LOAD_FACTOR)
			resize();
		return value;
	}

	/**
	 * Retrieves the value associated with the specified key from the map.
	 *
	 * @param index the index of the bucket in the map
	 * @param key   the key to search for
	 * @return the value associated with the key, or null if the key doesn't exist
	 */
	@Override
	protected V bucketGet(int index, K key) {
		Entry<K, V> current = map.get(index);
		while (current != null) {
			if (current.getKey().equals(key))
				return current.getValue();
			current = current.getNext();
		}
		return null;
	}

	/**
	 * Removes the entry associated with the specified key from the map.
	 *
	 * @param index the index of the bucket in the map
	 * @param key   the key to be removed
	 * @return the value associated with the key, or throws an exception if the key doesn't exist
	 * @throws UnsupportedOperationException if the key doesn't exist in the map
	 */
	@Override
	protected V bucketRemove(int index, K key) {
		Entry<K, V> current = map.get(index);
		Entry<K, V> prev = null;
		// Search for the key in the chain
		while (current != null) {
			if (current.getKey().equals(key)) {
				// Found the key, remove the entry from the chain
				if (prev == null) {
					// If the entry is at the head of the chain
					map.set(index, current.getNext());
				} else {
					// If the entry is in the middle or end of the chain
					prev.setNext(current.getNext());
				}
				// Update size
				size--;
				return current.getValue();
			}
			prev = current;
			current = current.getNext();
		}
		throw new UnsupportedOperationException("No element for the given key");
	}

	/**
	 * Checks if the map contains the specified key.
	 *
	 * @param key the key to check for
	 * @return true if the map contains the key, false otherwise
	 */
	@Override
	public boolean containsKey(K key) {
		int index = generateHash(key);
		Entry<K, V> current = map.get(index);
		while (current != null) {
			if (current.getKey().equals(key))
				return true;
			current = current.getNext();
		}
		return false;
	}

	/**
	 * Returns the size of the map.
	 *
	 * @return the number of key-value pairs in the map
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Checks if the map is empty.
	 *
	 * @return true if the map is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Clears the map by removing all key-value pairs.
	 */
	@Override
	public void clear() {
		map.clear(); // Clear the ArrayList
		size = 0;    // Reset size to 0
	}

	/**
	 * Resizes the map by doubling its capacity and rehashing all existing entries.
	 */
	@Override
	protected void resize() {
		this.setCapacity(this.getCapacity() * 2); // Double the capacity
		ArrayList<Entry<K, V>> newMap = new ArrayList<>(Collections.nCopies(this.getCapacity(), null));

		// Rehash all existing entries to the new map
		for (Entry<K, V> entry : map) {
			while (entry != null) {
				int newIndex = generateHash(entry.getKey()); // Calculate new index
				Entry<K, V> next = entry.getNext(); // Store next reference
				entry.setNext(newMap.get(newIndex)); // Insert entry into new map
				newMap.set(newIndex, entry); // Update new map
				entry = next; // Move to next entry in the chain
			}
		}

		map = newMap; // Update map reference
	}
	/**
	 * Retrieves all the keys from the map.
	 *
	 * @return a set of all the keys in the map
	 */
	public List<String> getAllKeys() {
		List<String> keys = new ArrayList<>();
		for (Entry<K, V> entry : map) {
			while (entry != null) {
				keys.add(entry.getKey().toString());
				entry = entry.getNext();
			}
		}
		return keys;
	}

	// Inner class to represent key-value pairs
	protected static class MapEntry<K, V> implements Entry<K, V> {
		K key;
		V value;
		Entry<K, V> next = null;

		MapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public Entry<K, V> getNext() {
			return this.next;
		}

		@Override
		public void setKey(K key) {
			this.key = key;
		}

		@Override
		public void setValue(V value) {
			this.value = value;
		}

		@Override
		public void setNext(Entry<K, V> nextEntry) {
			this.next = nextEntry;
		}
	}

	/**
	 * Returns an iterator over the entries in the map.
	 *
	 * @return an iterator over the entries in the map
	 */
	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new HashMapIterator();
	}

	// Inner class for HashMap Iterator
	private class HashMapIterator implements Iterator<Entry<K, V>> {

		private int currentIndex = -1; // Index of the current entry in the array list
		private Entry<K, V> currentEntry = null; // Current entry in the linked list

		HashMapIterator() {
			findNext(); // Initialize the iterator
		}

		@Override
		public boolean hasNext() {
			return currentEntry != null;
		}

		@Override
		public Entry<K, V> next() {
			if (!hasNext()) {
				throw new NoSuchElementException("No more elements in the map.");
			}
			Entry<K, V> nextEntry = currentEntry;
			currentEntry = currentEntry.getNext(); // Move to the next entry in the linked list
			if (currentEntry == null) {
				findNext(); // If the current linked list is exhausted, find the next non-empty entry in the array list
			}
			return nextEntry;
		}

		private void findNext() {
			currentIndex++;
			while (currentIndex < map.size() && map.get(currentIndex) == null) {
				currentIndex++;
			}
			if (currentIndex < map.size()) {
				currentEntry = map.get(currentIndex); // Set the current entry to the head of the non-empty linked list
			} else {
				currentEntry = null; // No more non-empty entries in the array list
			}
		}
	}
}
