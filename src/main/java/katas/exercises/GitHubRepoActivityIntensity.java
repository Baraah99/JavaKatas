package katas.exercises;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class GitHubRepoActivityIntensity {

    private static final String GITHUB_API_BASE_URL = "https://api.github.com/repos";

    /**
     * Fetches commit timestamps for the specified repository using the GitHub API.
     *
     * @param owner the owner of the repository
     * @param repo the name of the repository
     * @return a list of commit timestamps as Instant objects
     * @throws Exception if there is an error fetching or parsing the data
     */
    public static List<Instant> fetchCommitTimestamps(String owner, String repo) throws Exception {
        List<Instant> commitTimestamps = new ArrayList<>();
        String urlStr = GITHUB_API_BASE_URL + "/" + owner + "/" + repo + "/commits?per_page=100";
        boolean morePages = true;
        while (morePages) {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/vnd.github+json");
            conn.setRequestProperty("User-Agent", "Java");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response
            JSONArray commits = new JSONArray(response.toString());
            for (int i = 0; i < commits.length(); i++) {
                JSONObject commit = commits.getJSONObject(i);  // Get individual commit
                JSONObject commitDetails = commit.getJSONObject("commit");  // Get commit details
                JSONObject author = commitDetails.getJSONObject("author");  // Get author object
                String commitDateStr = author.getString("date");  // Get date from author
                Instant commitTime = Instant.parse(commitDateStr);  // Convert to Instant
                commitTimestamps.add(commitTime);
            }

            // Check if there's a next page (pagination)
            if (conn.getHeaderField("Link") != null && conn.getHeaderField("Link").contains("rel=\"next\"")) {
                String nextPageUrl = conn.getHeaderField("Link").split(",")[0].split(";")[0].replace("<", "").replace(">", "");
                urlStr = nextPageUrl; // Update to the next page URL
            } else {
                morePages = false;
            }

            conn.disconnect();
        }
        return commitTimestamps;
    }

    /**
     * Calculates the average time between consecutive commits.
     *
     * @param timestamps a list of commit timestamps
     * @return the average time in hours
     */
    public static double calculateAverageTimeBetweenCommits(List<Instant> timestamps) {
        if (timestamps.size() < 2) {
            return 0;
        }

        long totalTimeDifference = 0;
        for (int i = 1; i < timestamps.size(); i++) {
            totalTimeDifference += timestamps.get(i).toEpochMilli() - timestamps.get(i - 1).toEpochMilli();
        }

        // Calculate the average time difference in hours
        double averageTimeInMillis = totalTimeDifference / (double) (timestamps.size() - 1);
        return averageTimeInMillis / (1000 * 60 * 60); // Convert milliseconds to hours
    }

    public static void main(String[] args) {
        try {
            List<Instant> timestamps = fetchCommitTimestamps("torvalds", "linux");
            double avgTime = calculateAverageTimeBetweenCommits(timestamps);

            System.out.printf("The average time between commits in the repository is %.2f hours.%n", avgTime);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
