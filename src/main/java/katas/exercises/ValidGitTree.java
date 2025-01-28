package katas.exercises;

import java.util.*;

public class ValidGitTree {

    /**
     * Determines if a given tree structure represents a valid Git tree.
     *
     * A valid Git tree should:
     * 1. Have exactly one root (no parent).
     * 2. Contain no cycles.
     *
     * @param treeMap a map representing the Git tree (commit ID to list of child commit IDs)
     * @return true if the tree is a valid Git tree, false otherwise
     */
    public static boolean isValidGitTree(Map<String, List<String>> treeMap) {

        // Step 1: Identify root commits (those not in any child list)
        Set<String> allCommits = treeMap.keySet();
        Set<String> childCommits = new HashSet<>();

        for (List<String> children : treeMap.values()) {
            childCommits.addAll(children);
        }

        // Root commits are those in allCommits but not in childCommits
        Set<String> rootCommits = new HashSet<>(allCommits);
        rootCommits.removeAll(childCommits);

        // There must be exactly one root
        if (rootCommits.size() != 1) {
            return false;
        }

        // Step 2: Check for cycles using DFS
        String rootCommit = rootCommits.iterator().next();
        Set<String> visited = new HashSet<>();
        Set<String> recursionStack = new HashSet<>();

        if (hasCycle(treeMap, rootCommit, visited, recursionStack)) {
            return false;
        }

        return true;
    }

    /**
     * Helper function to detect cycles in the tree using DFS.
     */
    private static boolean hasCycle(Map<String, List<String>> treeMap, String commit, Set<String> visited, Set<String> recursionStack) {
        // If the commit is in the recursion stack, we have a cycle
        if (recursionStack.contains(commit)) {
            return true;
        }

        // If the commit is already visited, no need to check again
        if (visited.contains(commit)) {
            return false;
        }

        // Mark the current commit as visited and part of the recursion stack
        visited.add(commit);
        recursionStack.add(commit);

        // Recur for all children
        for (String child : treeMap.getOrDefault(commit, List.of())) {
            if (hasCycle(treeMap, child, visited, recursionStack)) {
                return true;
            }
        }

        // Remove from recursion stack when done with this commit
        recursionStack.remove(commit);
        return false;
    }

    public static void main(String[] args) {
        Map<String, List<String>> validTree = new HashMap<>();
        validTree.put("A", List.of("B", "C"));
        validTree.put("B", List.of("D"));
        validTree.put("C", List.of());
        validTree.put("D", List.of());

        Map<String, List<String>> invalidTree = new HashMap<>();
        invalidTree.put("A", List.of("B"));
        invalidTree.put("B", List.of("C"));
        invalidTree.put("C", List.of("A")); // cycle

        System.out.println("Is valid tree: " + isValidGitTree(validTree));
        System.out.println("Is valid tree: " + isValidGitTree(invalidTree));
    }
}

