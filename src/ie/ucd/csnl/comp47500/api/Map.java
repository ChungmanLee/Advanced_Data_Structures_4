package ie.ucd.csnl.comp47500.api;

public interface Map<K, V>  extends Iterable<Entry<K, V>>{

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    V put(K key, V value);

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    V get(K key);

    /**
     * Removes the mapping for a key from this map if it is present.
     *
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    V remove(K key);

    /**
     * Returns true if this map contains a mapping for the specified key.
     *
     * @param key the key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     */
    boolean containsKey(K key);

    /**
     * Returns the number of key-value mappings in this map.
     *
     * @return the number of key-value mappings in this map
     */
    int size();

    /**
     * Returns true if this map contains no key-value mappings.
     *
     * @return true if this map contains no key-value mappings
     */
    boolean isEmpty();

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    void clear();
}
