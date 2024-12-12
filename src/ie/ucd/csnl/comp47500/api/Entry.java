package ie.ucd.csnl.comp47500.api;

/**
 * An interface representing an entry in a key-value pair data structure.
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public interface Entry<K, V> {

	/**
	 * Returns the key associated with this entry.
	 *
	 * @return the key associated with this entry
	 */
	K getKey();

	/**
	 * Returns the value associated with this entry.
	 *
	 * @return the value associated with this entry
	 */
	V getValue();

	/**
	 * Returns the next entry in the data structure.
	 *
	 * @return the next entry in the data structure
	 */
	Entry<K, V> getNext();

	/**
	 * Sets the key associated with this entry.
	 *
	 * @param key the key to be set
	 */
	void setKey(K key);

	/**
	 * Sets the value associated with this entry.
	 *
	 * @param value the value to be set
	 */
	void setValue(V value);

	/**
	 * Sets the next entry in the data structure.
	 *
	 * @param nextEntry the next entry to be set
	 */
	void setNext(Entry<K, V> nextEntry);
}