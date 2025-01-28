package katas.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

/**
 * DroneFly Inc. operates a fleet of drones for package deliveries. Each drone can carry only one package at a time.
 * Customers send requests with the delivery start time, delivery duration,
 * and the payment they are willing to make for the delivery.
 *
 * Your task is to help DroneFly Inc. maximize its profit by finding the best combination of delivery
 * requests that a single drone can fulfill.
 *
 * Requests are sorted by their start times. Here's a sample input file with 4 delivery requests:
 *
 *  REQ01 0 5 50
 *  REQ02 3 7 80
 *  REQ03 5 9 60
 *  REQ04 6 9 70
 *
 *  The best combination is REQ01 and REQ04, with a total payment of 50+70=120.
 */
public class Lags {

    /**
     * Represents a delivery request.
     */
    static class Request {
        String id;
        int startTime;
        int endTime;
        int payment;

        public Request(String id, int startTime, int duration, int payment) {
            this.id = id;
            this.startTime = startTime;
            this.endTime = startTime + duration;
            this.payment = payment;
        }
    }

    /**
     * Finds the maximum profit from a list of delivery requests that do not overlap.
     *
     * @param requests the list of delivery requests, sorted by start time
     * @return the maximum profit
     */
    public static int maximizeProfit(List<Request> requests) {
        // Sort requests by their end time
        requests.sort(Comparator.comparingInt(r -> r.endTime));

        // Dynamic programming arrays
        int[] dp = new int[requests.size()];
        dp[0] = requests.get(0).payment;

        for (int i = 1; i < requests.size(); i++) {
            // Include current request
            int includeProfit = requests.get(i).payment;
            int lastNonConflicting = findLastNonConflicting(requests, i);
            if (lastNonConflicting != -1) {
                includeProfit += dp[lastNonConflicting];
            }

            // Exclude current request
            int excludeProfit = dp[i - 1];

            // Take the maximum of include and exclude
            dp[i] = Math.max(includeProfit, excludeProfit);
        }

        return dp[requests.size() - 1];
    }

    /**
     * Finds the index of the last request that does not conflict with the given request.
     *
     * @param requests the list of delivery requests
     * @param index the index of the current request
     * @return the index of the last non-conflicting request, or -1 if none exists
     */
    private static int findLastNonConflicting(List<Request> requests, int index) {
        for (int i = index - 1; i >= 0; i--) {
            if (requests.get(i).endTime <= requests.get(index).startTime) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<Request> requests = new ArrayList<>();
        requests.add(new Request("REQ01", 0, 5, 50));
        requests.add(new Request("REQ02", 3, 7, 80));
        requests.add(new Request("REQ03", 5, 4, 60));
        requests.add(new Request("REQ04", 6, 3, 70));

        int maxProfit = maximizeProfit(requests);

        System.out.println("Maximum Profit: " + maxProfit); // Output: 120
    }
}
