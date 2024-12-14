package katas.exercises;

public class TrueCounter {

    /**
     * Counts the number of true values in the given boolean array.
     *
     * @param array the boolean array to check
     * @return the count of true values in the array
     */
    public static int countTrueValues(boolean[] array) {
        if (array == null || array.length == 0) {
            return 0; // Return 0 for null or empty arrays
        }

        int count = 0;
        for (boolean value : array) {
            if (value) {
                count++; // Increment count for each true value
            }
        }
        return count;
    }

    public static void main(String[] args) {
        boolean[] sampleArray = {true, false, true, true, false};
        int trueCount = countTrueValues(sampleArray);
        System.out.println(trueCount);
    }
}
