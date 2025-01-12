package katas.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CircularBufferTest {

    private CircularBuffer buffer;

    @BeforeEach
    void setUp() {
        buffer = new CircularBuffer(3); // Initialize buffer with capacity 3
    }

    @Test
    void testAddAndGet() {
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);

        assertEquals(1, buffer.get(), "The first element retrieved should be 1.");
        assertEquals(2, buffer.get(), "The second element retrieved should be 2.");
        assertEquals(3, buffer.get(), "The third element retrieved should be 3.");
    }

    @Test
    void testAddOverCapacity() {
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        buffer.add(4); // Overwrite the oldest element (1)

        assertEquals(2, buffer.get(), "The first element retrieved should be 2 after overwriting.");
        assertEquals(3, buffer.get(), "The second element retrieved should be 3.");
        assertEquals(4, buffer.get(), "The third element retrieved should be 4.");
    }

    @Test
    void testIsEmpty() {
        assertTrue(buffer.isEmpty(), "Buffer should initially be empty.");

        buffer.add(1);
        assertFalse(buffer.isEmpty(), "Buffer should not be empty after adding an element.");

        buffer.get();
        assertTrue(buffer.isEmpty(), "Buffer should be empty after retrieving the only element.");
    }

    @Test
    void testIsFull() {
        assertFalse(buffer.isFull(), "Buffer should not be full initially.");

        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        assertTrue(buffer.isFull(), "Buffer should be full after adding elements equal to its capacity.");

        buffer.get();
        assertFalse(buffer.isFull(), "Buffer should not be full after retrieving an element.");
    }

    @Test
    void testGetFromEmptyBuffer() {
        assertEquals(-1, buffer.get(), "Retrieving from an empty buffer should return -1.");
    }

    @Test
    void testAddGetAddSequence() {
        buffer.add(1);
        buffer.add(2);

        assertEquals(1, buffer.get(), "The first element retrieved should be 1.");

        buffer.add(3);
        buffer.add(4); // Overwrites 2, as buffer is now full

        assertEquals(2, buffer.get(), "The second element retrieved should be 2.");
        assertEquals(3, buffer.get(), "The third element retrieved should be 3.");
        assertEquals(4, buffer.get(), "The fourth element retrieved should be 4.");
    }

    @Test
    void testOverwriteBehavior() {
        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        buffer.add(4); // Overwrites 1
        buffer.add(5); // Overwrites 2

        assertEquals(3, buffer.get(), "The first element retrieved should be 3.");
        assertEquals(4, buffer.get(), "The second element retrieved should be 4.");
        assertEquals(5, buffer.get(), "The third element retrieved should be 5.");
    }
}
