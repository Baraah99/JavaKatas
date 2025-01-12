package katas.exercises;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RequirementsCoverage {

    /**
     * In software testing, it's often required to select a minimal set of test cases that cover all the requirements.
     * You are given a set of test cases and their associated covered requirements.
     * Your task is to select the minimal subset of test cases such that all requirements are covered.
     *
     * For example, you have the following test cases and requirements they cover:
     *
     * testCases = [
     *   [1, 2, 3],   // Test case 0 covers requirements 1, 2, 3
     *   [1, 4],      // Test case 1 covers requirements 1, 4
     *   [2, 3, 4],   // Test case 2 covers requirements 2, 3, 4
     *   [1, 5],      // Test case 3 covers requirements 1, 5
     *   [3, 5]       // Test case 4 covers requirements 3, 5
     * ]
     *
     * @param testCases a list of test cases, where each test case is a list of requirements it covers
     * @return a list of indices of the minimal subset of test cases that covers all requirements
     */
    public static List<Integer> selectMinimalTestCases(List<List<Integer>> testCases) {

        // Set to keep track of all the covered requirements
        Set<Integer> coveredRequirements = new HashSet<>();
        // List to store the indices of the selected test cases
        List<Integer> selectedTestCases = new ArrayList<>();

        // Keep track of all requirements
        Set<Integer> allRequirements = new HashSet<>();
        for (List<Integer> testCase : testCases) {
            allRequirements.addAll(testCase);
        }

        // While there are still uncovered requirements
        while (coveredRequirements.size() < allRequirements.size()) {
            int bestTestCaseIndex = -1;
            int bestCoverage = 0;
            Set<Integer> bestCoverageSet = new HashSet<>();

            // Find the test case that covers the most uncovered requirements
            for (int i = 0; i < testCases.size(); i++) {
                List<Integer> testCase = testCases.get(i);
                Set<Integer> uncoveredRequirements = new HashSet<>(testCase);
                uncoveredRequirements.removeAll(coveredRequirements); // Remove already covered

                // If this test case covers more uncovered requirements
                if (uncoveredRequirements.size() > bestCoverage) {
                    bestTestCaseIndex = i;
                    bestCoverage = uncoveredRequirements.size();
                    bestCoverageSet = uncoveredRequirements;
                }
            }

            // Add the selected test case's index and covered requirements to the result
            selectedTestCases.add(bestTestCaseIndex);
            coveredRequirements.addAll(bestCoverageSet);
        }

        return selectedTestCases;
    }

    public static void main(String[] args) {
        List<List<Integer>> testCases = List.of(
                List.of(1, 2, 3),
                List.of(1, 4),
                List.of(2, 3, 4),
                List.of(1, 5),
                List.of(3, 5)
        );

        List<Integer> result = selectMinimalTestCases(testCases);
        System.out.println(result); // Expected output: [2, 3]
    }
}
