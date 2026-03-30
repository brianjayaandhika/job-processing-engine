package com.jobprocessingengine.job;

/**
 * Enumerates the categories of jobs the engine can process.
 * Workers and processors use JobType to determine how to handle a Job
 * without resorting to if-else chains on the Job object itself.
 */
public enum JobType {

    /** A job that produces printed or text-based output. */
    PRINT,

    /** A job that sends an email notification. */
    EMAIL
}
