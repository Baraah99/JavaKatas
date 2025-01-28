package katas.exercises;

import java.util.*;

/**
 * Find the median of a stream of integers.
 *
 * The numbers will be provided one at a time in a dynamic data stream, and after each new number is added,
 * your function should efficiently compute the median of all numbers seen so far.
 *
 * Adding a number: O(log n).
 * Finding the median: O(1) or O(log n).
 *
 * Hint: Consider using two heaps (min-heap and max-heap) to efficiently maintain the order of elements.
 */
public class MedianFinder {

    private PriorityQueue<Integer> maxHeap; // Max-heap for the smaller half of the numbers
    private PriorityQueue<Integer> minHeap; // Min-heap for the larger half of the numbers

    /**
     * Initializes the MedianFinder object.
     */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    /**
     * Adds a number to the data stream.
     *
     * @param num the number to be added
     */
    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // Balance the heaps to maintain the size property
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    /**
     * Finds and returns the median of the data stream.
     *
     * @return the median as a double
     */
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();

        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median: " + medianFinder.findMedian()); // Output: 1.5

        medianFinder.addNum(3);
        System.out.println("Median: " + medianFinder.findMedian()); // Output: 2.0

        medianFinder.addNum(5);
        System.out.println("Median: " + medianFinder.findMedian()); // Output: 2.5
    }
}
