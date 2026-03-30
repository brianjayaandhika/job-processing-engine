package com.jobprocessingengine.main;

import com.jobprocessingengine.coordinator.JobCoordinator;
import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobType;
import com.jobprocessingengine.processor.EmailJobProcessor;
import com.jobprocessingengine.processor.JobProcessor;
import com.jobprocessingengine.processor.PrintJobProcessor;
import com.jobprocessingengine.queue.InMemoryJobQueue;
import com.jobprocessingengine.queue.JobQueue;
import com.jobprocessingengine.store.InMemoryJobStore;
import com.jobprocessingengine.store.JobStore;
import com.jobprocessingengine.worker.WorkerPool;

import java.util.List;

/**
 * Application entry point for the job processing engine.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Wire all components together (poor-man's dependency injection)</li>
 *   <li>Start the engine, submit sample jobs, and initiate graceful shutdown</li>
 * </ul>
 *
 * <p>TODO: Replace sample jobs with a real submission mechanism (e.g., CLI input or file reader).
 */
public class Main {

    /**
     * Application entry point.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        // --- Wiring ---
        JobStore jobStore = new InMemoryJobStore();
        JobQueue jobQueue = new InMemoryJobQueue();

        List<JobProcessor> processors = List.of(
                new PrintJobProcessor(),
                new EmailJobProcessor()
        );

        // TODO: Make pool size configurable (e.g., via args or a config file)
        WorkerPool workerPool = new WorkerPool(3, jobQueue, jobStore, processors);

        JobCoordinator coordinator = new JobCoordinator(jobQueue, jobStore, workerPool);

        // --- Start engine ---
        // TODO: coordinator.start();

        // --- Submit sample jobs ---
        // TODO: Uncomment once JobCoordinator.submit() is implemented
        // coordinator.submit(new Job(JobType.PRINT, "Hello, World!"));
        // coordinator.submit(new Job(JobType.EMAIL, "user@example.com"));

        // --- Shutdown engine ---
        // TODO: coordinator.shutdown();

        System.out.println("Job Processing Engine skeleton initialised. Implement TODO sections to activate.");
    }
}
