package katas.exercises;

public class ArrayDifference {

    /**
     * Finds the difference between the largest and smallest numbers in the array.
     *
     * @param numbers the array of integer
     * @return the difference between the largest and smallest numbers
     */
    public static int findDifference(int[] numbers) {
        // Check if the array is empty to avoid errors
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty or null");
        }

        int max = numbers[0]; // Initialize max with the first element
        int min = numbers[0]; // Initialize min with the first element

        // Loop through the array to find the maximum and minimum values
        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        // Return the difference between max and min
        return max - min;
    }

    public static void main(String[] args) {
        int[] sampleArray = {10, 3, 5, 6, 20, -2};
        int difference = findDifference(sampleArray);
        System.out.println(difference);
    }
}
