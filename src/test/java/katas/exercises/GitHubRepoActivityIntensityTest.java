package katas.exercises;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GitHubRepoActivityIntensityTest {

    @Test
    void testCalculateAverageTimeBetweenCommits() throws Exception {
        List<Instant> mockTimestamps = List.of(
                Instant.parse("2023-12-01T12:00:00Z"),
                Instant.parse("2023-12-01T14:00:00Z"),
                Instant.parse("2023-12-01T18:00:00Z"),
                Instant.parse("2023-12-02T06:00:00Z")
        );

        double averageTime = GitHubRepoActivityIntensity.calculateAverageTimeBetweenCommits(mockTimestamps);
    }

    @Test
    void testCalculateAverageTimeBetweenCommitsSingleTimestamp() {
        List<Instant> timestamps = List.of(Instant.parse("2023-01-01T12:00:00Z"));
        double avgTime = GitHubRepoActivityIntensity.calculateAverageTimeBetweenCommits(timestamps);
        assertEquals(0, avgTime, "Average time should be 0 for less than 2 timestamps.");
    }


}
