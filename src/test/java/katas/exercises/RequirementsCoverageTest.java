package katas.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RequirementsCoverageTest {

    @Test
    void testSelectMinimalTestCases() {
        // Test case 1: General case with overlapping requirements
        List< List<Integer> > testCases1 = List.of(
                List.of(1, 2, 3),
                List.of(1, 4),
                List.of(2, 3, 4),
                List.of(1, 5),
                List.of(3, 5)
        );



        // Test case 2: Single test case covering all requirements
        List<List<Integer>> testCases2 = List.of(
                List.of(1, 2, 3, 4, 5)
        );

        List<Integer> result2 = RequirementsCoverage.selectMinimalTestCases(testCases2);
        assertEquals(List.of(0), result2, "Only the first test case should be selected");

        // Test case 3: No test cases (edge case)
        List<List<Integer>> testCases3 = List.of();

        List<Integer> result3 = RequirementsCoverage.selectMinimalTestCases(testCases3);
        assertTrue(result3.isEmpty(), "No test cases should be selected");

        // Test case 4: No requirements (edge case)
        List<List<Integer>> testCases4 = List.of(
                List.of()
        );

        List<Integer> result4 = RequirementsCoverage.selectMinimalTestCases(testCases4);
        assertTrue(result4.isEmpty(), "No test cases should be selected when no requirements are present");

        // Test case 5: Test case with full coverage, but multiple options
        List<List<Integer>> testCases5 = List.of(
                List.of(1, 2),
                List.of(2, 3),
                List.of(3, 4),
                List.of(4, 5)
        );
    }

    @Test
    void testEdgeCaseSingleRequirement() {
        // Test case 6: Single requirement with multiple test cases
        List<List<Integer>> testCases6 = List.of(
                List.of(1),
                List.of(1),
                List.of(1)
        );

        List<Integer> result6 = RequirementsCoverage.selectMinimalTestCases(testCases6);
        assertEquals(List.of(0), result6, "Only one test case should be selected when all cover the same requirement");
    }

    @Test
    void testEdgeCaseMultipleTestCasesForOneRequirement() {
        // Test case 7: Multiple test cases, but they all cover the same requirement
        List<List<Integer>> testCases7 = List.of(
                List.of(1),
                List.of(1),
                List.of(1)
        );

        List<Integer> result7 = RequirementsCoverage.selectMinimalTestCases(testCases7);
        assertEquals(List.of(0), result7, "Only the first test case should be selected when they all cover the same requirement");
    }

}
