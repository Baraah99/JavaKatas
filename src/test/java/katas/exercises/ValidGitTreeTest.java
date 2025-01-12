package katas.exercises;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidGitTreeTest {


    @Test
    public void testValidTree() {
        // Create a valid tree (no cycles, exactly one root)
        Map<String, List<String> > validTree = new HashMap<>();
        validTree.put("A", List.of("B", "C"));
        validTree.put("B", List.of("D"));
        validTree.put("C", List.of());
        validTree.put("D", List.of());

        // Assert the tree is valid
        assertTrue(ValidGitTree.isValidGitTree(validTree));
    }

    @Test
    public void testInvalidTreeWithCycle() {
        // Create an invalid tree with a cycle (C -> A)
        Map<String, List<String>> invalidTree = new HashMap<>();
        invalidTree.put("A", List.of("B"));
        invalidTree.put("B", List.of("C"));
        invalidTree.put("C", List.of("A")); // cycle

        // Assert the tree is invalid due to the cycle
        assertFalse(ValidGitTree.isValidGitTree(invalidTree));
    }

    @Test
    public void testInvalidTreeWithNoRoot() {
        // Create an invalid tree with no root (multiple root candidates)
        Map<String, List<String>> invalidTreeNoRoot = new HashMap<>();
        invalidTreeNoRoot.put("A", List.of("B"));
        invalidTreeNoRoot.put("B", List.of("C"));
        invalidTreeNoRoot.put("C", List.of("B")); // B and C form a cycle, A has no root

        // Assert the tree is invalid due to multiple roots or no clear root
        assertFalse(ValidGitTree.isValidGitTree(invalidTreeNoRoot));
    }

    @Test
    public void testValidTreeWithMultipleBranches() {
        // Create a valid tree with multiple branches and a single root
        Map<String, List<String>> validTreeWithBranches = new HashMap<>();
        validTreeWithBranches.put("A", List.of("B", "C"));
        validTreeWithBranches.put("B", List.of("D", "E"));
        validTreeWithBranches.put("C", List.of("F"));
        validTreeWithBranches.put("D", List.of());
        validTreeWithBranches.put("E", List.of());
        validTreeWithBranches.put("F", List.of());

        // Assert the tree is valid
        assertTrue(ValidGitTree.isValidGitTree(validTreeWithBranches));
    }

    @Test
    public void testSingleCommitTree() {
        // Create a tree with a single commit (root)
        Map<String, List<String>> singleCommitTree = new HashMap<>();
        singleCommitTree.put("A", List.of());

        // Assert the tree is valid (a single root commit is allowed)
        assertTrue(ValidGitTree.isValidGitTree(singleCommitTree));
    }
}
