package com.jobprocessingengine.processor;

import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobType;

/**
 * {@link JobProcessor} implementation for {@link JobType#PRINT} jobs.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Handle all jobs whose type is {@link JobType#PRINT}</li>
 *   <li>Produce printed/text-based output from the job's payload</li>
 * </ul>
 */
public class PrintJobProcessor implements JobProcessor {

    /** {@inheritDoc} */
    @Override
    public void process(Job job) throws JobProcessingException {
        // TODO: Implement print processing logic using job.getPayload()
        throw new UnsupportedOperationException("PrintJobProcessor.process() not yet implemented");
    }

    /**
     * Returns {@code true} only for {@link JobType#PRINT} jobs.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Job job) {
        return job != null && JobType.PRINT.equals(job.getType());
    }
}
