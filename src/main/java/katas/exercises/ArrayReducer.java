package katas.exercises;

public class ArrayReducer {

    /**
     * Modifies the array so that each element becomes the difference between
     * itself and its predecessor. The first element remains unchanged.
     *
     * @param numbers the array of integers to modify
     */
    public static void reduceArray(int[] numbers) {

        // Check if the array is valid and contains more than one element
        if (numbers == null || numbers.length < 2) {
            return;
        }

        // Initialize a variable to store the previous element
        int prev = numbers[0];  // First element remains unchanged

        // Iterate over the array starting from the second element
        for (int i = 1; i < numbers.length; i++) {
            // Modify the current element to be the difference between itself and the previous element
            int current = numbers[i];
            numbers[i] = current - prev;
            prev = current;  // Update previous to the current element
        }


    }

    public static void main(String[] args) {
        int[] sampleArray = {10, 15, 7, 20, 25};
        System.out.println("Original array: ");
        printArray(sampleArray);
        reduceArray(sampleArray);
        System.out.println("Reduced array: ");
        printArray(sampleArray);
    }

    /**
     * Helper method to print the elements of an array.
     *
     * @param array the array to print
     */
    public static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
