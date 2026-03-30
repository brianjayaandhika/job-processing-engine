package com.jobprocessingengine.job;

/**
 * Represents the lifecycle states of a Job as it moves through the system.
 * Used by JobStore to track and report job progress safely across threads.
 */
public enum JobStatus {

    /** Job has been created and submitted but not yet queued. */
    PENDING,

    /** Job is in the queue waiting to be picked up by a Worker. */
    QUEUED,

    /** Job is actively being processed by a Worker. */
    IN_PROGRESS,

    /** Job has completed successfully. */
    COMPLETED,

    /** Job has failed during processing. */
    FAILED
}
