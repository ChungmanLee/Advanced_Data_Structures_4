package ie.ucd.csnl.comp47500.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ie.ucd.csnl.comp47500.api.Entry;
import ie.ucd.csnl.comp47500.impl.HashMapImpl;

/**
 * This class contains unit tests for the HashMapImpl class.
 */
public class HashMapImplTest {

    /**
     * Test case for the put() and get() methods of HashMapImpl.
     */
    @Test
    void testPutAndGet() {
        HashMapImpl<String, Integer> map = new HashMapImpl<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        
        // Assuming you have an instance of HashMapImpl<String, Integer> called "map"

        // Get an iterator for the map
        Iterator<Entry<String, Integer>> iterator = map.iterator();

        // Iterate over the entries
        while (iterator.hasNext()) {
            Entry<String, Integer> entry = iterator.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value);
        }

        assertEquals(1, map.get("One"));
        assertEquals(2, map.get("Two"));
        assertEquals(3, map.get("Three"));
    }

    /**
     * Test case for the containsKey() method of HashMapImpl.
     */
    @Test
    void testContainsKey() {
        HashMapImpl<String, Integer> map = new HashMapImpl<>();
        map.put("One", 1);
        map.put("Two", 2);

        assertTrue(map.containsKey("One"));
        assertTrue(map.containsKey("Two"));
        assertFalse(map.containsKey("Three"));
    }

    /**
     * Test case for the remove() method of HashMapImpl.
     */
    @Test
    void testRemove() {
        HashMapImpl<String, Integer> map = new HashMapImpl<>();
        map.put("One", 1);
        map.put("Two", 2);

        assertEquals(1, map.remove("One"));
        assertNull(map.get("One"));
        assertEquals(1, map.size());
    }

    /**
     * Test case for the size() and isEmpty() methods of HashMapImpl.
     */
    @Test
    void testSizeAndIsEmpty() {
        HashMapImpl<String, Integer> map = new HashMapImpl<>();
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());

        map.put("One", 1);
        assertFalse(map.isEmpty());
        assertEquals(1, map.size());
    }
    
    /**
     * Test case for the resize() method of HashMapImpl.
     */
    @Test
    void testResize() {
        HashMapImpl<Integer, String> map = new HashMapImpl<>();
        
        // Add elements to trigger resize
        for (int i = 0; i < 20; i++) {
            map.put(i, "Value" + i);
        }
        
        // Check if all elements are present
        for (int i = 0; i < 20; i++) {
            assertEquals("Value" + i, map.get(i));
        }
    }
}
