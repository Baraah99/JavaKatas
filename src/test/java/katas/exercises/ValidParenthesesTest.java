package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidParenthesesTest {

    @Test
    public void testValidParentheses() {
        // Test cases with valid parentheses
        assertTrue(ValidParentheses.isValidParentheses("()"));
        assertTrue(ValidParentheses.isValidParentheses("()[]{}"));
        assertTrue(ValidParentheses.isValidParentheses("{[()]}"));

        // Test cases with invalid parentheses
        assertFalse(ValidParentheses.isValidParentheses("(]"));
        assertFalse(ValidParentheses.isValidParentheses("([)]"));
        assertFalse(ValidParentheses.isValidParentheses("){[()]}"));
        assertFalse(ValidParentheses.isValidParentheses("([}])"));

        // Edge case: empty string (valid since there are no unmatched parentheses)
        assertTrue(ValidParentheses.isValidParentheses(""));

        // Edge case: single character (not valid as it cannot form a pair)
        assertFalse(ValidParentheses.isValidParentheses("("));
    }
}
