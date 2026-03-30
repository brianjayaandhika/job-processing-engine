package com.jobprocessingengine.processor;

import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobType;

/**
 * {@link JobProcessor} implementation for {@link JobType#EMAIL} jobs.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Handle all jobs whose type is {@link JobType#EMAIL}</li>
 *   <li>Send email notifications based on the job's payload</li>
 * </ul>
 */
public class EmailJobProcessor implements JobProcessor {

    /** {@inheritDoc} */
    @Override
    public void process(Job job) throws JobProcessingException {
        // TODO: Implement email sending logic using job.getPayload()
        throw new UnsupportedOperationException("EmailJobProcessor.process() not yet implemented");
    }

    /**
     * Returns {@code true} only for {@link JobType#EMAIL} jobs.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Job job) {
        return job != null && JobType.EMAIL.equals(job.getType());
    }
}
