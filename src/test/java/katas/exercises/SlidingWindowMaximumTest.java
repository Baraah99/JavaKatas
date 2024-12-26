package katas.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlidingWindowMaximumTest {

    @Test
    public void testSlidingWindowMaximum() {
        // Test Case 1: Normal case with multiple sliding windows
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        List<Integer> result1 = SlidingWindowMaximum.maxSlidingWindow(nums1, k1);
        assertEquals(List.of(3, 3, 5, 5, 6, 7), result1, "Test case 1 failed");

        // Test Case 2: Window size is 1 (each element is its own maximum)
        int[] nums2 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k2 = 1;
        List<Integer> result2 = SlidingWindowMaximum.maxSlidingWindow(nums2, k2);
        assertEquals(List.of(1, 3, -1, -3, 5, 3, 6, 7), result2, "Test case 2 failed");

        // Test Case 3: Window size is equal to array length (the max of the whole array)
        int[] nums3 = {1, 3, 2, 5, 4};
        int k3 = 5;
        List<Integer> result3 = SlidingWindowMaximum.maxSlidingWindow(nums3, k3);
        assertEquals(List.of(5), result3, "Test case 3 failed");

        // Test Case 4: Array with negative numbers
        int[] nums4 = {-1, -3, -5, -2, -8};
        int k4 = 2;
        List<Integer> result4 = SlidingWindowMaximum.maxSlidingWindow(nums4, k4);
        assertEquals(List.of(-1, -3, -2, -2), result4, "Test case 4 failed");

        // Test Case 5: Array with increasing elements
        int[] nums5 = {1, 2, 3, 4, 5};
        int k5 = 3;
        List<Integer> result5 = SlidingWindowMaximum.maxSlidingWindow(nums5, k5);
        assertEquals(List.of(3, 4, 5), result5, "Test case 5 failed");

        // Test Case 6: Array with decreasing elements
        int[] nums6 = {5, 4, 3, 2, 1};
        int k6 = 3;
        List<Integer> result6 = SlidingWindowMaximum.maxSlidingWindow(nums6, k6);
        assertEquals(List.of(5, 4, 3), result6, "Test case 6 failed");

        // Test Case 7: Array with single element
        int[] nums7 = {10};
        int k7 = 1;
        List<Integer> result7 = SlidingWindowMaximum.maxSlidingWindow(nums7, k7);
        assertEquals(List.of(10), result7, "Test case 7 failed");

        // Test Case 8: Empty array
        int[] nums8 = {};
        int k8 = 3;
        List<Integer> result8 = SlidingWindowMaximum.maxSlidingWindow(nums8, k8);
        assertEquals(List.of(), result8, "Test case 8 failed");

        // Test Case 9: Window size larger than array length (no valid windows)
        int[] nums9 = {1, 2};
        int k9 = 3;
        List<Integer> result9 = SlidingWindowMaximum.maxSlidingWindow(nums9, k9);
        assertEquals(List.of(), result9, "Test case 9 failed");
    }
}
