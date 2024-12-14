package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestCommonPrefixTest {

    @Test
    public void testLongestCommonPrefix() {
        // Test case: Common prefix exists
        String[] input1 = {"flower", "flow", "flight"};
        String expected1 = "fl";
        assertEquals(expected1, LongestCommonPrefix.longestCommonPrefix(input1));

        // Test case: No common prefix
        String[] input2 = {"dog", "racecar", "car"};
        String expected2 = "";
        assertEquals(expected2, LongestCommonPrefix.longestCommonPrefix(input2));

        // Test case: Single string in the array
        String[] input3 = {"apple"};
        String expected3 = "apple";
        assertEquals(expected3, LongestCommonPrefix.longestCommonPrefix(input3));

        // Test case: Empty array
        String[] input4 = {};
        String expected4 = "";
        assertEquals(expected4, LongestCommonPrefix.longestCommonPrefix(input4));

        // Test case: Null array
        String[] input5 = null;
        String expected5 = "";
        assertEquals(expected5, LongestCommonPrefix.longestCommonPrefix(input5));

        // Test case: All strings are the same
        String[] input6 = {"test", "test", "test"};
        String expected6 = "test";
        assertEquals(expected6, LongestCommonPrefix.longestCommonPrefix(input6));

        // Test case: Prefix is an empty string
        String[] input7 = {"apple", "banana", "cherry"};
        String expected7 = "";
        assertEquals(expected7, LongestCommonPrefix.longestCommonPrefix(input7));

        // Test case: Prefix is a substring of one string
        String[] input8 = {"intention", "intermediate", "internal"};
        String expected8 = "inte";
        assertEquals(expected8, LongestCommonPrefix.longestCommonPrefix(input8));
    }
}
