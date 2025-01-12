package katas.exercises;

import katas.exercises.OrderedMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class OrderedMapTest {

    @Test
    void testPutAndGet() {
        OrderedMap<String, Integer> orderedMap = new OrderedMap<>();
        orderedMap.put("One", 1);
        orderedMap.put("Two", 2);

        assertEquals(1, orderedMap.get("One"));
        assertEquals(2, orderedMap.get("Two"));
        assertNull(orderedMap.get("Three")); // Non-existent key
    }

    @Test
    void testPreserveOrder() {
        OrderedMap<String, Integer> orderedMap = new OrderedMap<>();
        orderedMap.put("One", 1);
        orderedMap.put("Two", 2);
        orderedMap.put("Three", 3);

        List<String> keys = orderedMap.keys();
        assertEquals(List.of("One", "Two", "Three"), keys);
    }

    @Test
    void testUpdateValuePreservesOrder() {
        OrderedMap<String, Integer> orderedMap = new OrderedMap<>();
        orderedMap.put("One", 1);
        orderedMap.put("Two", 2);
        orderedMap.put("Three", 3);

        orderedMap.put("Two", 22); // Update the value of "Two"
        List<String> keys = orderedMap.keys();

        assertEquals(List.of("One", "Two", "Three"), keys);
        assertEquals(22, orderedMap.get("Two"));
    }

    @Test
    void testRemove() {
        OrderedMap<String, Integer> orderedMap = new OrderedMap<>();
        orderedMap.put("One", 1);
        orderedMap.put("Two", 2);
        orderedMap.put("Three", 3);

        orderedMap.remove("Two");
        List<String> keys = orderedMap.keys();

        assertEquals(List.of("One", "Three"), keys);
        assertNull(orderedMap.get("Two")); // Ensure "Two" is removed
    }

    @Test
    void testSize() {
        OrderedMap<String, Integer> orderedMap = new OrderedMap<>();
        assertEquals(0, orderedMap.size());

        orderedMap.put("One", 1);
        assertEquals(1, orderedMap.size());

        orderedMap.put("Two", 2);
        assertEquals(2, orderedMap.size());

        orderedMap.remove("One");
        assertEquals(1, orderedMap.size());
    }

    @Test
    void testClear() {
        OrderedMap<String, Integer> orderedMap = new OrderedMap<>();
        orderedMap.put("One", 1);
        orderedMap.put("Two", 2);

        orderedMap.clear();
        assertEquals(0, orderedMap.size());
        assertTrue(orderedMap.keys().isEmpty());
        assertNull(orderedMap.get("One"));
    }

    @Test
    void testDuplicateKeys() {
        OrderedMap<String, Integer> orderedMap = new OrderedMap<>();
        orderedMap.put("One", 1);
        orderedMap.put("One", 11); // Update the value of "One"

        assertEquals(1, orderedMap.size());
        assertEquals(List.of("One"), orderedMap.keys());
        assertEquals(11, orderedMap.get("One"));
    }

    @Test
    void testNonExistentKeyRemoval() {
        OrderedMap<String, Integer> orderedMap = new OrderedMap<>();
        orderedMap.put("One", 1);
        orderedMap.remove("Two"); // Removing a non-existent key should do nothing

        assertEquals(1, orderedMap.size());
        assertEquals(List.of("One"), orderedMap.keys());
    }
}
