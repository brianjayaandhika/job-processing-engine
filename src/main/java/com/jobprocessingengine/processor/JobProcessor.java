package com.jobprocessingengine.processor;

import com.jobprocessingengine.job.Job;

/**
 * Strategy interface for processing a single {@link Job}.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Define the contract for all job-processing strategies</li>
 *   <li>Allow {@link com.jobprocessingengine.worker.Worker} to delegate processing
 *       without coupling to a specific implementation</li>
 * </ul>
 *
 * <p>Each implementation handles exactly one {@link com.jobprocessingengine.job.JobType},
 * eliminating the need for if-else chains in workers.
 */
public interface JobProcessor {

    /**
     * Processes the given job.
     *
     * <p>Implementations are responsible for:
     * <ul>
     *   <li>Executing the business logic defined by the job's type and payload</li>
     *   <li>Throwing a {@link JobProcessingException} if processing fails so the
     *       caller can update job status to {@link com.jobprocessingengine.job.JobStatus#FAILED}</li>
     * </ul>
     *
     * @param job the job to process; must not be {@code null}
     * @throws JobProcessingException if an error occurs during processing
     */
    void process(Job job) throws JobProcessingException;

    /**
     * Returns {@code true} if this processor is capable of handling the given job.
     *
     * @param job the job to evaluate
     * @return {@code true} if this processor supports the job's type
     */
    boolean supports(Job job);
}
