package katas.exercises;

import java.util.Stack;

/**
 * Design a stack that supports standard stack operations (push, pop, top) and also retrieves
 * the minimum element in constant time.
 */
public class MinStack {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    /** Initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /**
     * Pushes a value onto the stack.
     *
     * @param val the value to push
     */
    public void push(int val) {
        stack.push(val);
        // Push the minimum value so far onto the minStack
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    /**
     * Removes the top element from the stack.
     */
    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        int removed = stack.pop();
        // If the removed element is the current minimum, pop it from minStack as well
        if (removed == minStack.peek()) {
            minStack.pop();
        }
    }

    /**
     * Gets the top element of the stack.
     *
     * @return the top element
     */
    public int top() {
        return stack.isEmpty() ? -1 : stack.peek();
    }

    /**
     * Retrieves the minimum element in the stack.
     *
     * @return the minimum element
     */
    public int getMin() {
        return minStack.isEmpty() ? -1 : minStack.peek();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin()); // Output: -3
        stack.pop();
        System.out.println(stack.top()); // Output: 0
        System.out.println(stack.getMin()); // Output: -2
    }
}
