package katas.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueWithFailoverTest {

    private QueueWithFailover queue;

    @BeforeEach
    void setUp() {
        queue = new QueueWithFailover(3); // Initialize with a 3-second timeout
    }

    @Test
    void testIsEmptyInitially() {
        assertTrue(queue.isEmpty(), "Queue should be empty initially.");
    }

    @Test
    void testSendJobAndSize() {
        queue.sendJob("Job 1");
        queue.sendJob("Job 2");

        assertEquals(2, queue.size(), "Queue size should reflect the number of added jobs.");
        assertFalse(queue.isEmpty(), "Queue should not be empty after adding jobs.");
    }

    @Test
    void testGetJob() {
        queue.sendJob("Job 1");
        String job = queue.getJob();

        assertEquals("Job 1", job, "The first retrieved job should be 'Job 1'.");
        assertEquals(0, queue.size(), "Queue size should decrease after retrieving a job.");
        assertEquals(1, queue.inFlightSize(), "In-flight size should increase after retrieving a job.");
    }

    @Test
    void testJobDone() {
        queue.sendJob("Job 1");
        String job = queue.getJob();

        queue.jobDone(job);

        assertEquals(0, queue.inFlightSize(), "In-flight size should decrease after marking a job as done.");
    }

    @Test
    void testJobDoneInvalidJob() {
        queue.sendJob("Job 1");
        queue.getJob();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            queue.jobDone("Non-existent Job");
        });

        assertEquals("Job not found in hidden jobs", exception.getMessage());
    }

    @Test
    void testGetJobFromEmptyQueue() {
        Exception exception = assertThrows(QueueWithFailover.EmptyQueueException.class, () -> {
            queue.getJob();
        });

        assertEquals("The job queue is empty", exception.getMessage());
    }

    @Test
    void testFailoverExpiredJobs() throws InterruptedException {
        queue.sendJob("Job 1");
        String job = queue.getJob();

        Thread.sleep(4000); // Wait for the job to expire
        queue.returnExpiredJobsToQueue();

        assertEquals(1, queue.size(), "Expired jobs should be returned to the main queue.");
        assertEquals(0, queue.inFlightSize(), "In-flight size should decrease after returning expired jobs.");
    }

    @Test
    void testMultipleJobsAndFailover() throws InterruptedException {
        queue.sendJob("Job 1");
        queue.sendJob("Job 2");

        String job1 = queue.getJob();
        String job2 = queue.getJob();

        assertEquals(0, queue.size(), "All jobs should be in-flight.");
        assertEquals(2, queue.inFlightSize(), "Both jobs should be in-flight.");

        Thread.sleep(4000); // Wait for both jobs to expire
        queue.returnExpiredJobsToQueue();

        assertEquals(2, queue.size(), "All expired jobs should return to the main queue.");
        assertEquals(0, queue.inFlightSize(), "No jobs should be in-flight after failover.");
    }

    @Test
    void testSendJobAfterFailover() throws InterruptedException {
        queue.sendJob("Job 1");
        queue.getJob();

        Thread.sleep(4000); // Wait for the job to expire
        queue.returnExpiredJobsToQueue();

        queue.sendJob("Job 2");

        assertEquals(2, queue.size(), "Queue size should include returned and newly added jobs.");
    }
}
