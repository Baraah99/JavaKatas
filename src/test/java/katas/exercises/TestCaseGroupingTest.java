package katas.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCaseGroupingTest {


    @Test
    public void testGroupTestCases_singleGroup() {
        // Case where all test cases belong to a single group of size 5
        List<Integer> testCaseGroupSizes = List.of(5);
        List<List<Integer>> expected = List.of(
                List.of(0, 1, 2, 3, 4)
        );

        List<List<Integer>> result = TestCaseGrouping.groupTestCases(testCaseGroupSizes);

        // Assert that the result matches the expected output
        assertEquals(expected, result);
    }

    @Test
    public void testGroupTestCases_empty() {
        // Case where no test cases are provided
        List<Integer> testCaseGroupSizes = List.of();
        List<List<Integer>> expected = List.of();  // No groups, should return empty list

        List<List<Integer>> result = TestCaseGrouping.groupTestCases(testCaseGroupSizes);

        // Assert that the result is an empty list
        assertEquals(expected, result);
    }

    @Test
    public void testGroupTestCases_allSingleGroup() {
        // Case where each test case is in its own group (group size 1 for each)
        List<Integer> testCaseGroupSizes = List.of(1, 1, 1, 1, 1);
        List<List<Integer>> expected = List.of(
                List.of(0),  // Group with only test case 0
                List.of(1),  // Group with only test case 1
                List.of(2),  // Group with only test case 2
                List.of(3),  // Group with only test case 3
                List.of(4)   // Group with only test case 4
        );

        List<List<Integer>> result = TestCaseGrouping.groupTestCases(testCaseGroupSizes);

        // Assert that the result matches the expected output
        assertEquals(expected, result);
    }

    @Test
    public void testGroupTestCases_multipleGroups() {
        // Case where some test cases have larger group sizes
        List<Integer> testCaseGroupSizes = List.of(1, 2, 2, 3);
        List<List<Integer>> expected = List.of(
                List.of(0),       // Group with test case 0
                List.of(1, 2),    // Group with test cases 1 and 2
                List.of(3, 4),    // Group with test cases 3 and 4
                List.of(5, 6, 7)  // Group with test cases 5, 6, and 7
        );

        List<List<Integer>> result = TestCaseGrouping.groupTestCases(testCaseGroupSizes);

        // Assert that the result matches the expected output
        assertEquals(expected, result);
    }
}
