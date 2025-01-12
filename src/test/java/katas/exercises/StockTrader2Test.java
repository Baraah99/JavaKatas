package katas.exercises;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockTrader2Test {
    @Test
    public void testMaxProfit() {
        // Test case 1: Normal case with multiple prices and multiple transactions
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        assertEquals(7, StockTrader2.maxProfit(prices1), "Test case 1 failed");

        // Test case 2: No profit can be made (prices are decreasing)
        int[] prices2 = {7, 6, 4, 3, 1};
        assertEquals(0, StockTrader2.maxProfit(prices2), "Test case 2 failed");

        // Test case 3: All prices are the same, no profit can be made
        int[] prices3 = {5, 5, 5, 5, 5};
        assertEquals(0, StockTrader2.maxProfit(prices3), "Test case 3 failed");

        // Test case 4: Only one price, no transactions can be made
        int[] prices4 = {10};
        assertEquals(0, StockTrader2.maxProfit(prices4), "Test case 4 failed");

        // Test case 5: Prices increase steadily, maximum profit is the difference between the last and first price
        int[] prices5 = {1, 2, 3, 4, 5};
        assertEquals(4, StockTrader2.maxProfit(prices5), "Test case 5 failed");


        // Test case 7: Prices decrease initially and then increase
        int[] prices7 = {5, 1, 2, 4, 7};
        assertEquals(6, StockTrader2.maxProfit(prices7), "Test case 7 failed");

        // Test case 8: Edge case with no prices (empty array)
        int[] prices8 = {};
        assertEquals(0, StockTrader2.maxProfit(prices8), "Test case 8 failed");

        // Test case 9: Edge case with only one price
        int[] prices9 = {5};
        assertEquals(0, StockTrader2.maxProfit(prices9), "Test case 9 failed");
    }
}
