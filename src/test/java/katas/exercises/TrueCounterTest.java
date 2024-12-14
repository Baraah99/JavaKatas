package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrueCounterTest {

    @Test
    public void testCountTrueValues() {
        // Test case: normal array with a mix of true and false values
        assertEquals( 3, TrueCounter.countTrueValues( new boolean[]{ true, false, true, true, false } ),
                "Expected count is 3 for input {true, false, true, true, false}" );

        // Test case: array with all true values
        assertEquals( 4, TrueCounter.countTrueValues( new boolean[]{ true, true, true, true } ),
                "Expected count is 4 for input {true, true, true, true}" );

        // Test case: array with all false values
        assertEquals( 0, TrueCounter.countTrueValues( new boolean[]{ false, false, false } ),
                "Expected count is 0 for input {false, false, false}" );

        // Test case: empty array
        assertEquals( 0, TrueCounter.countTrueValues( new boolean[]{ } ),
                "Expected count is 0 for an empty array" );

        // Test case: null array
        assertEquals( 0, TrueCounter.countTrueValues( null ),
                "Expected count is 0 for a null array" );

        // Test case: single true value
        assertEquals( 1, TrueCounter.countTrueValues( new boolean[]{ true } ),
                "Expected count is 1 for input {true}" );

        // Test case: single false value
        assertEquals( 0, TrueCounter.countTrueValues( new boolean[]{ false } ),
                "Expected count is 0 for input {false}" );

    }
}
