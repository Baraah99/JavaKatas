package katas.exercises;

import java.util.*;

public class QueueWithFailover {
    /**
     * A job queue data structure with failover support.
     */
    private int jobTimeout;
    private Queue<String> jobs;
    private Map<String, Long> hiddenJobs;

    public QueueWithFailover(int jobTimeout) {
        /**
         * Initialize an empty job queue.
         */
        this.jobTimeout = jobTimeout * 1000; // Convert to milliseconds
        this.jobs = new LinkedList<>();
        this.hiddenJobs = new HashMap<>();
    }

    public boolean isEmpty() {
        /**
         * Check if the job queue is empty.
         *
         * @return boolean: True if the job queue is empty, False otherwise.
         */
        return jobs.isEmpty() && hiddenJobs.isEmpty();
    }

    public void sendJob(String job) {
        /**
         * Send a job to the job queue.
         *
         * @param job The job to be added to the queue.
         */
        jobs.offer(job);
    }

    public String getJob() throws EmptyQueueException {
        /**
         * Retrieve and return a job from the front of the job queue.
         *
         * @return String: The job at the front of the queue.
         * @throws EmptyQueueException: If the job queue is empty.
         */
        if (isEmpty()) {
            throw new EmptyQueueException("The job queue is empty");
        }

        // Check the hidden jobs that need to be returned to the main queue
        returnExpiredJobsToQueue();

        String job = jobs.poll();
        if (job != null) {
            // Mark the job as hidden for processing
            hiddenJobs.put(job, System.currentTimeMillis());
        }

        return job;
    }

    public void jobDone(String job) {
        /**
         * This function is called when a consumer completes a consumed job.
         * The job should be deleted permanently (from the hidden jobs).
         *
         * @param job The job to be deleted permanently from the queue.
         * @throws IllegalArgumentException: If the job is not found in the hidden jobs.
         */
        if (!hiddenJobs.containsKey(job)) {
            throw new IllegalArgumentException("Job not found in hidden jobs");
        }

        hiddenJobs.remove(job);
    }

    public int size() {
        /**
         * Return the number of jobs in the job queue.
         *
         * @return int: The number of jobs in the queue.
         */
        return jobs.size();
    }

    public int inFlightSize() {
        /**
         * Return the number of hidden jobs.
         *
         * @return int: The number of hidden jobs in the queue.
         */
        return hiddenJobs.size();
    }

    public void returnExpiredJobsToQueue() {
        /**
         * Return hidden jobs that were retrieved more than `jobTimeout` seconds ago back to the job queue.
         */
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<String, Long>> iterator = hiddenJobs.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Long> entry = iterator.next();
            if (currentTime - entry.getValue() > jobTimeout) {
                // Job expired, return it to the queue
                jobs.offer(entry.getKey());
                iterator.remove(); // Remove from hidden jobs
            }
        }
    }

    public static void main(String[] args) {
        QueueWithFailover jobQueue = new QueueWithFailover(3);

        jobQueue.sendJob("Job 1");
        jobQueue.sendJob("Job 2");
        jobQueue.sendJob("Job 3");

        System.out.println("Job Queue Size: " + jobQueue.size());

        try {
            String currentJob = jobQueue.getJob();
            System.out.println("Current Job: " + currentJob);
            jobQueue.jobDone(currentJob);

            currentJob = jobQueue.getJob();
            System.out.println("Current Job: " + currentJob);

            // Simulate job expiration
            Thread.sleep(4000);

            jobQueue.returnExpiredJobsToQueue();

            jobQueue.jobDone(currentJob);
        } catch (EmptyQueueException | IllegalArgumentException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("Job Queue Size: " + jobQueue.size());
        System.out.println("In-Flight Jobs: " + jobQueue.inFlightSize());
    }

    static class EmptyQueueException extends RuntimeException {
        public EmptyQueueException(String message) {
            super(message);
        }
    }
}
