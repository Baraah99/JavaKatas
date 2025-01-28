package katas.exercises;

import java.util.*;

public class OrderedMap<K, V> {
    private List<K> keys;
    private Map<K, V> map;

    public OrderedMap() {
        keys = new ArrayList<>();
        map = new HashMap<>();
    }

    /**
     * Add a key-value pair to the map.
     * If the key already exists, update its value while preserving the order.
     */
    public void put(K key, V value) {
        if (!map.containsKey(key)) {
            keys.add(key); // Add the key to the list if it doesn't already exist
        }
        map.put(key, value); // Put the key-value pair in the map
    }

    /**
     * Retrieve the value associated with a given key.
     * @param key: The key whose value is to be retrieved.
     * @return: The value associated with the key, or null if the key does not exist.
     */
    public V get(K key) {
        return map.get(key); // Retrieve the value from the map
    }

    /**
     * Remove a key-value pair from the map.
     * @param key: The key to be removed.
     */
    public void remove(K key) {
        if (map.containsKey(key)) {
            map.remove(key);
            keys.remove(key); // Also remove the key from the list to preserve order
        }
    }

    /**
     * Return all keys in the order they were added.
     * @return: A list of keys in insertion order.
     */
    public List<K> keys() {
        return new ArrayList<>(keys); // Return a new list of keys in insertion order
    }

    /**
     * Return the number of elements in the map.
     * @return: The size of the map.
     */
    public int size() {
        return map.size(); // Return the size of the map
    }

    /**
     * Remove all key-value pairs from the map.
     */
    public void clear() {
        map.clear();
        keys.clear(); // Clear both the map and the list of keys
    }

    public static void main(String[] args) {
        OrderedMap<String, Integer> orderedMap = new OrderedMap<>();

        orderedMap.put("One", 1);
        orderedMap.put("Two", 2);
        orderedMap.put("Three", 3);

        System.out.println("Keys in order: " + orderedMap.keys());
        System.out.println("Value of 'Two': " + orderedMap.get("Two"));

        orderedMap.remove("Two");
        System.out.println("Keys after removal: " + orderedMap.keys());

        orderedMap.put("Two", 22);
        System.out.println("Keys after re-adding 'Two': " + orderedMap.keys());

        System.out.println("Map size: " + orderedMap.size());

        orderedMap.clear();
        System.out.println("Map size after clearing: " + orderedMap.size());
    }
}
