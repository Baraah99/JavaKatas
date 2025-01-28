package katas.exercises;

public class StringDigitSum {

    /**
     * Calculates the sum of all digits in the given string.
     *
     * @param input the string containing digits and other characters
     * @return the sum of all digits in the string
     */
    public static int sumOfDigits(String input) {

       if (input == null || input.isEmpty()) {
            return 0; // Return 0 for null or empty strings
        }

        int sum = 0;

        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) { // Check if the character is a digit
                sum += Character.getNumericValue(c); // Convert digit to its numeric value and add to sum
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        String input1 = "abc123";
        String input2 = "5 cats and 2 dogs";
        String input3 = "No digits here!";

        System.out.println("Sum of digits in '" + input1 + "': " + sumOfDigits(input1));
        System.out.println("Sum of digits in '" + input2 + "': " + sumOfDigits(input2));
        System.out.println("Sum of digits in '" + input3 + "': " + sumOfDigits(input3));
    }
}
