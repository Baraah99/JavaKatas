package katas.exercises;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListFlattenTest {


    @Test
    public void testFlattenList() {
        // Test case: Fully nested list
        List<Object> nestedList1 = List.of(1, List.of(2, 3), List.of(4, List.of(5, 6)), 7);
        List<Integer> expected1 = List.of(1, 2, 3, 4, 5, 6, 7);
        assertEquals(expected1, ListFlatten.flattenList(nestedList1));

        // Test case: Empty list
        List<Object> nestedList2 = List.of();
        List<Integer> expected2 = List.of();
        assertEquals(expected2, ListFlatten.flattenList(nestedList2));

        // Test case: Flat list
        List<Object> nestedList3 = List.of(1, 2, 3, 4);
        List<Integer> expected3 = List.of(1, 2, 3, 4);
        assertEquals(expected3, ListFlatten.flattenList(nestedList3));

        // Test case: Single nested list
        List<Object> nestedList4 = List.of(List.of(1, 2, 3));
        List<Integer> expected4 = List.of(1, 2, 3);
        assertEquals(expected4, ListFlatten.flattenList(nestedList4));

        // Test case: Deeply nested list
        List<Object> nestedList5 = List.of(List.of(List.of(List.of(1, 2)), 3), List.of(4, List.of(5)));
        List<Integer> expected5 = List.of(1, 2, 3, 4, 5);
        assertEquals(expected5, ListFlatten.flattenList(nestedList5));

        // Test case: Mixed element types (non-integer elements ignored)
        List<Object> nestedList6 = List.of(1, "a", List.of(2, 3), List.of(4, "b"));
        List<Integer> expected6 = List.of(1, 2, 3, 4);
        assertEquals(expected6, ListFlatten.flattenList(nestedList6));

        // Test case: Nested list with only non-integer elements
        List<Object> nestedList7 = List.of("a", List.of("b", "c"), List.of(List.of("d")));
        List<Integer> expected7 = List.of();
        assertEquals(expected7, ListFlatten.flattenList(nestedList7));

        // Test case: Null input
        assertThrows(NullPointerException.class, () -> ListFlatten.flattenList(null));

    }
}
