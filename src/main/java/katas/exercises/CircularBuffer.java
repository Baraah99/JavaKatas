package katas.exercises;

public class CircularBuffer {
    private int[] buffer;
    private int head;
    private int tail;
    private int size;

    /**
     * Initializes the circular buffer with a fixed capacity.
     *
     * @param capacity the maximum number of elements the buffer can hold
     */
    public CircularBuffer(int capacity) {
        buffer = new int[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    /**
     * Adds an element to the buffer.
     *
     * @param val the value to add
     */
    public void add(int val) {
        // If the buffer is full, we overwrite the oldest element
        if (size == buffer.length) {
            head = (head + 1) % buffer.length; // Move head to the next position (overwrite the oldest element)
        } else {
            size++;
        }
        buffer[tail] = val; // Add new value at the tail position
        tail = (tail + 1) % buffer.length; // Move tail to the next position
    }

    /**
     * Retrieves the oldest element from the buffer.
     *
     * @return the oldest element, or -1 if the buffer is empty
     */
    public int get() {
        if (isEmpty()) {
            return -1;
        }
        int value = buffer[head];
        head = (head + 1) % buffer.length; // Move head to the next position
        size--;
        return value;
    }

    /**
     * Checks if the buffer is full.
     *
     * @return true if the buffer is full, false otherwise
     */
    public boolean isFull() {
        return size == buffer.length;
    }

    /**
     * Checks if the buffer is empty.
     *
     * @return true if the buffer is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        CircularBuffer buffer = new CircularBuffer(3);

        buffer.add(1);
        buffer.add(2);
        buffer.add(3);
        System.out.println(buffer.get()); // Output: 1
        buffer.add(4);
        System.out.println(buffer.get()); // Output: 2
        buffer.add(5);
        System.out.println(buffer.isFull()); // Output: true
    }
}
