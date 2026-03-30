package com.jobprocessingengine.processor;

/**
 * Signals that a fatal error occurred while processing a job.
 *
 * <p>Thrown by {@link JobProcessor} implementations and caught by
 * {@link com.jobprocessingengine.worker.Worker} to mark the job as
 * {@link com.jobprocessingengine.job.JobStatus#FAILED}.
 */
public class JobProcessingException extends Exception {

    /**
     * Creates a new {@code JobProcessingException} with the given message.
     *
     * @param message a description of the failure
     */
    public JobProcessingException(String message) {
        super(message);
    }

    /**
     * Creates a new {@code JobProcessingException} with the given message and cause.
     *
     * @param message a description of the failure
     * @param cause   the underlying exception that caused the failure
     */
    public JobProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
