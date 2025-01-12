package katas.exercises;

public class SemanticVersionComparator {

    /**
     * Compares two semantic version strings to determine their relative order.
     *
     * @param version1 the first version string (e.g., "MAJOR.MINOR.PATCH")
     * @param version2 the second version string (e.g., "MAJOR.MINOR.PATCH")
     * @return -1 if version1 < version2, 1 if version1 > version2, 0 if they are equal
     */
    public static int compareVersions(String version1, String version2) {

        // Split both version strings by "."
        String[] v1Parts = version1.split("\\.");
        String[] v2Parts = version2.split("\\.");

        // Convert each part to an integer and compare
        for (int i = 0; i < 3; i++) {
            int v1 = Integer.parseInt(v1Parts[i]);
            int v2 = Integer.parseInt(v2Parts[i]);

            // Compare the corresponding parts
            if (v1 > v2) {
                return 1;  // version1 is greater
            } else if (v1 < v2) {
                return -1; // version1 is smaller
            }
        }

        // If all parts are equal
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(compareVersions("1.0.0", "1.0.1")); // Expected: -1
        System.out.println(compareVersions("2.1.0", "1.9.9")); // Expected: 1
        System.out.println(compareVersions("1.2.3", "1.2.3")); // Expected: 0
    }
}
