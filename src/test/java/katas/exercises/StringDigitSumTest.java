package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringDigitSumTest {


    @Test
    public void testSumOfDigits() {
        // Test case: string with digits and letters
        assertEquals(6, StringDigitSum.sumOfDigits("a1b2c3"), "Expected sum is 6 for input 'a1b2c3'");

        // Test case: string with no digits
        assertEquals(0, StringDigitSum.sumOfDigits("abcdef"), "Expected sum is 0 for input 'abcdef'");

        // Test case: string with digits only
        assertEquals(15, StringDigitSum.sumOfDigits("12345"), "Expected sum is 15 for input '12345'");

        // Test case: string with special characters and digits
        assertEquals(6, StringDigitSum.sumOfDigits("1a!2#3$"), "Expected sum is 6 for input '1a!2#3$'");

        // Test case: empty string
        assertEquals(0, StringDigitSum.sumOfDigits(""), "Expected sum is 0 for an empty string");

        // Test case: null input
        assertEquals(0, StringDigitSum.sumOfDigits(null), "Expected sum is 0 for null input");

        // Test case: string with spaces and digits
        assertEquals(10, StringDigitSum.sumOfDigits(" 1 2 3 4 "), "Expected sum is 10 for input ' 1 2 3 4 '");

        // Test case: string with all zero digits
        assertEquals(0, StringDigitSum.sumOfDigits("0000"), "Expected sum is 0 for input '0000'");

        // Test case: string with very large input
        assertEquals(45, StringDigitSum.sumOfDigits("1234567890"), "Expected sum is 45 for input '1234567890'");

        // Test case: string with digits scattered
        assertEquals(10, StringDigitSum.sumOfDigits("a1b2c3d4"), "Expected sum is 10 for input 'a1b2c3d4'");
    }
}
