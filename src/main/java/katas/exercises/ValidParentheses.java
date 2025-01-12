package katas.exercises;

import java.util.Stack;

public class ValidParentheses {

    /**
     * Checks if a given string has valid parentheses (try in O(n)).
     *
     * A string has valid parentheses if:
     * 1. Every opening parenthesis has a matching closing parenthesis.
     * 2. The parentheses are correctly nested.
     *
     * @param s the input string containing parentheses
     * @return true if the string has valid parentheses, false otherwise
     */
    public static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        // Iterate over each character in the string
        for (char c : s.toCharArray()) {
            // If it's an opening bracket, push it onto the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // If it's a closing bracket, check if it matches the top of the stack
            else if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else if (c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            } else if (c == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else {
                return false; // Mismatch or stack is empty when expecting a closing bracket
            }
        }

        // If the stack is empty, all parentheses are matched correctly
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String validInput = "()[]{}";
        String invalidInput1 = "(]";
        String invalidInput2 = "([)]";
        String validInputNested = "{[()]}";

        System.out.println("Is valid: " + isValidParentheses(validInput));
        System.out.println("Is valid: " + isValidParentheses(invalidInput1));
        System.out.println("Is valid: " + isValidParentheses(invalidInput2));
        System.out.println("Is valid: " + isValidParentheses(validInputNested));
    }
}
