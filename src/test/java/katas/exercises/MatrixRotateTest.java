package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MatrixRotateTest {

    @Test
    public void testRotateMatrix() {
        // Test case 1: 3x3 Matrix
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] expected1 = {
                {7, 4, 1},
                {8, 5, 2},
                {9, 6, 3}
        };
        MatrixRotate.rotateMatrix(matrix1);
        assertArrayEquals(expected1, matrix1);

        // Test case 2: 2x2 Matrix
        int[][] matrix2 = {
                {1, 2},
                {3, 4}
        };
        int[][] expected2 = {
                {3, 1},
                {4, 2}
        };
        MatrixRotate.rotateMatrix(matrix2);
        assertArrayEquals(expected2, matrix2);

        // Test case 3: 4x4 Matrix
        int[][] matrix3 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[][] expected3 = {
                {13, 9, 5, 1},
                {14, 10, 6, 2},
                {15, 11, 7, 3},
                {16, 12, 8, 4}
        };
        MatrixRotate.rotateMatrix(matrix3);
        assertArrayEquals(expected3, matrix3);

        // Test case 4: 1x1 Matrix (Edge case)
        int[][] matrix4 = {
                {1}
        };
        int[][] expected4 = {
                {1}
        };
        MatrixRotate.rotateMatrix(matrix4);
        assertArrayEquals(expected4, matrix4);

        // Test case 5: Empty matrix (Edge case)
        int[][] matrix5 = {};
        int[][] expected5 = {};
        MatrixRotate.rotateMatrix(matrix5);
        assertArrayEquals(expected5, matrix5);
    }
}
