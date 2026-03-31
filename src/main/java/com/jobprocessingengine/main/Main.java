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


public class Main {

    /**
     * Application entry point.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {


        System.out.println("Job Processing Engine skeleton initialised. Implement TODO sections to activate.");
    }
}
