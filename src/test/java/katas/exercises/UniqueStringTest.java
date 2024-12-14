package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UniqueStringTest {

    @Test
    public void testIsUnique() {
        // Test case: normal string with duplicate characters (case-insensitive)
        assertFalse( UniqueString.isUnique( "Hello" ), "Expected false for 'Hello' due to duplicate 'l'" );

        // Test case: normal string with unique characters
        assertTrue( UniqueString.isUnique( "World" ), "Expected true for 'World' with all unique characters" );

        // Test case: string with mixed case duplicate characters
        assertFalse( UniqueString.isUnique( "AaBbCc" ), "Expected false for 'AaBbCc' due to duplicate 'a' and 'A'" );

        // Test case: empty string
        assertTrue( UniqueString.isUnique( "" ), "Expected true for an empty string" );

        // Test case: null input
        assertTrue( UniqueString.isUnique( null ), "Expected true for null input" );

        // Test case: single character string
        assertTrue( UniqueString.isUnique( "A" ), "Expected true for a single character string" );

        // Test case: string with all identical characters
        assertFalse( UniqueString.isUnique( "aaaaa" ), "Expected false for 'aaaaa' with all duplicate characters" );

        // Test case: string with spaces
        assertFalse( UniqueString.isUnique( "A B C D A" ), "Expected false for 'A B C D A' due to duplicate 'A'" );

        // Test case: string with numbers and letters
        assertTrue( UniqueString.isUnique( "123Abc" ), "Expected true for '123Abc' with unique alphanumeric characters" );

        // Test case: string with special characters
        assertFalse( UniqueString.isUnique( "!@#$%!" ), "Expected false for '!@#$%!' due to duplicate '!'" );

    }

}
