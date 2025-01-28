package katas.exercises;

import java.util.Stack;

public class MaxStorageCapacity {

    /**
     *
     * Imagine a series of storage containers placed side by side, where the height of each container
     * is given by an integer in the array. Your task is to find the largest rectangular area that
     * can be formed using one or more of these containers.
     *
     * For example:
     * Input: containers = [2, 1, 5, 6, 2, 3]
     * Output: 10
     * Explanation: The largest rectangle is formed between containers at indices 2 and 3
     * with height 5 and width 2.
     *
     * @param containers an array of integers representing the heights of containers
     * @return the area of the largest rectangle formed between containers
     */
    public static int maxStorageArea(int[] containers) {
        // Initialize a stack to store indices
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0; // Variable to store the maximum area
        int n = containers.length;

        for (int i = 0; i < n; i++) {
            // While the current height is less than the height of the container at the stack's top,
            // calculate the area with the container at the stack's top as the smallest height.
            while (!stack.isEmpty() && containers[i] < containers[stack.peek()]) {
                int height = containers[stack.pop()]; // Height of the container
                int width = stack.isEmpty() ? i : i - stack.peek() - 1; // Width calculation
                maxArea = Math.max(maxArea, height * width);
            }
            // Push the current index onto the stack
            stack.push(i);
        }

        // Calculate the area for the remaining indices in the stack
        while (!stack.isEmpty()) {
            int height = containers[stack.pop()];
            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] containers = {2, 1, 5, 6, 2, 3};

        int result = maxStorageArea(containers);
        System.out.println("Max storage area: " + result); // Expected output: 10
    }
}
