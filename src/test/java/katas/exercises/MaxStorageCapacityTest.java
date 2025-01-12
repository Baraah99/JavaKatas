package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxStorageCapacityTest {


    @Test
    public void testMaxStorageArea_SimpleCase() {
        int[] containers = {2, 1, 5, 6, 2, 3};
        int expected = 10; // The largest rectangle has height 5 and width 2
        assertEquals(expected, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    public void testMaxStorageArea_SingleElement() {
        int[] containers = {5};
        int expected = 5; // Only one container, area is its height
        assertEquals(expected, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    public void testMaxStorageArea_AllSameHeight() {
        int[] containers = {4, 4, 4, 4};
        int expected = 16; // Largest rectangle includes all containers
        assertEquals(expected, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    public void testMaxStorageArea_DecreasingHeights() {
        int[] containers = {6, 5, 4, 3, 2, 1};
        int expected = 12; // Largest rectangle includes first two containers (height 6, width 2)
        assertEquals(expected, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    public void testMaxStorageArea_IncreasingHeights() {
        int[] containers = {1, 2, 3, 4, 5};
        int expected = 9; // Largest rectangle includes last three containers (height 3, width 3)
        assertEquals(expected, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    public void testMaxStorageArea_EmptyArray() {
        int[] containers = {};
        int expected = 0; // No containers, area is 0
        assertEquals(expected, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    public void testMaxStorageArea_ZeroHeightContainers() {
        int[] containers = {0, 0, 0};
        int expected = 0; // All containers have zero height
        assertEquals(expected, MaxStorageCapacity.maxStorageArea(containers));
    }

    @Test
    public void testMaxStorageArea_MixedHeights() {
        int[] containers = {2, 1, 2};
        int expected = 3; // Largest rectangle includes the first and last containers (height 1, width 3)
        assertEquals(expected, MaxStorageCapacity.maxStorageArea(containers));
    }
}
