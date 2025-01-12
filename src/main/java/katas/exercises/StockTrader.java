package katas.exercises;

public class StockTrader {

    /**
     * Finds the maximum profit that can be achieved by buying and selling the stock ONCE.
     *
     * Aim for O(n)
     *
     * @param prices an array of prices on each day
     * @return the maximum profit, or 0 if no profit can be achieved
     */
    public static int maxProfit(int[] prices) {

        // If the prices array is empty or has only one price, no profit can be made
        if (prices == null || prices.length < 2) {
            return 0;
        }

        // Initialize variables for the minimum price and maximum profit
        int minPrice = prices[0];
        int maxProfit = 0;

        // Iterate over the prices to calculate the maximum profit
        for (int i = 1; i < prices.length; i++) {
            // Calculate the potential profit if we sold at the current price
            int profit = prices[i] - minPrice;

            // Update the maxProfit if we found a higher profit
            maxProfit = Math.max(maxProfit, profit);

            // Update the minPrice if we found a lower price
            minPrice = Math.min(minPrice, prices[i]);
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] stockPrices = {7, 1, 5, 3, 6, 4};
        int profit = maxProfit(stockPrices);
        System.out.println("Maximum Profit: " + profit);  // should be 5
    }
}
