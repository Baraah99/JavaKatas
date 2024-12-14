package katas.exercises;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayReducerTest {

    @Test
    public void testReduceArray() {
        // Normal case: an array of integers
        int[] normalArray = {10, 15, 7, 20, 25};
        ArrayReducer.reduceArray(normalArray);
        assertArrayEquals(new int[]{10, 5, -8, 13, 5}, normalArray, "The array was not reduced correctly");

        // Edge case: Empty array
        int[] emptyArray = {};
        ArrayReducer.reduceArray(emptyArray);
        assertArrayEquals(new int[]{}, emptyArray, "The empty array should remain unchanged");

        // Edge case: Single element array
        int[] singleElementArray = {5};
        ArrayReducer.reduceArray(singleElementArray);
        assertArrayEquals(new int[]{5}, singleElementArray, "The single element array should remain unchanged");

        // Edge case: Array with identical elements
        int[] identicalArray = {6, 6, 6, 6, 6};
        ArrayReducer.reduceArray(identicalArray);
        assertArrayEquals(new int[]{6, 0, 0, 0, 0}, identicalArray, "The array with identical elements was not reduced correctly");

        // Edge case: Array with descending order
        int[] descendingArray = {32, 22, 12, 2, -8};
        ArrayReducer.reduceArray(descendingArray);
        assertArrayEquals(new int[]{32, -10, -10, -10, -10}, descendingArray, "The descending array was not reduced correctly");

        // Edge case: Array with length 2
        int[] lengthTwoArray = {5, 3};
        ArrayReducer.reduceArray(lengthTwoArray);
        assertArrayEquals(new int[]{5, -2}, lengthTwoArray, "The 2-element array was not reduced correctly");
    }
}
