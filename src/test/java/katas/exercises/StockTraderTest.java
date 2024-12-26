package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockTraderTest {
    @Test
    public void testMaxProfit() {
        // Test case 1: Normal case with multiple prices
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        assertEquals(5, StockTrader.maxProfit(prices1), "Test case 1 failed");

        // Test case 2: No profit can be made (prices are decreasing)
        int[] prices2 = {7, 6, 4, 3, 1};
        assertEquals(0, StockTrader.maxProfit(prices2), "Test case 2 failed");

        // Test case 3: Prices are the same, so no profit can be made
        int[] prices3 = {5, 5, 5, 5, 5};
        assertEquals(0, StockTrader.maxProfit(prices3), "Test case 3 failed");

        // Test case 4: Only one price, so no profit can be made
        int[] prices4 = {10};
        assertEquals(0, StockTrader.maxProfit(prices4), "Test case 4 failed");

        // Test case 5: Price increases, so maximum profit is the difference between the last and first price
        int[] prices5 = {1, 2, 3, 4, 5};
        assertEquals(4, StockTrader.maxProfit(prices5), "Test case 5 failed");

        // Test case 6: Price decreases and then increases
        int[] prices6 = {10, 3, 1, 5, 8, 2, 7};
        assertEquals(7, StockTrader.maxProfit(prices6), "Test case 6 failed");

        // Test case 7: Prices are already at their maximum difference (only two prices)
        int[] prices7 = {2, 6};
        assertEquals(4, StockTrader.maxProfit(prices7), "Test case 7 failed");
    }
}
