package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayDifferenceTest {

    @Test
    public void testFindDifference() {
        // Test with a regular numbers in the array
        int[] numbers = {10, 3, 5, 6, 20, -2};
        int result = ArrayDifference.findDifference(numbers);
        assertEquals(22, result, "The difference should be 22 (20 - (-2))");

        // Test for array containing a single element (difference should be 0)
        int[] singleElementArray = {8};
        result = ArrayDifference.findDifference(singleElementArray);
        assertEquals(0, result, "The difference should be 0 for a single element array");

        // Test array where all elements are the same (difference should be 0)
        int[] sameElementArray = {3, 3, 3, 3};
        result = ArrayDifference.findDifference(sameElementArray);
        assertEquals(0, result, "The difference should be 0 when all elements are the same");

        // Test array of negative numbers
        int[] negativeArray = {-5, -8, -2, -10};
        result = ArrayDifference.findDifference(negativeArray);
        assertEquals(8, result, "The difference should be 8 (-2 - (-10))");

        // Test with an array containing zeros
        int[] zeroArray = {0, 0, 0, 0};
        result = ArrayDifference.findDifference(zeroArray);
        assertEquals(0, result, "The difference should be 0 when all elements are 0");


        // Test with an array where the largest and smallest elements are at different positions
        int[] mixedArray = {1, -1, 5, 3, 0};
        result = ArrayDifference.findDifference(mixedArray);
        assertEquals(6, result, "The difference should be 6 (5 - (-1))");

        // Test with an empty array (should throw IllegalArgumentException)
        int[] emptyArray = {};
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayDifference.findDifference(emptyArray);
        }, "Should throw IllegalArgumentException for an empty array");
    }
}
