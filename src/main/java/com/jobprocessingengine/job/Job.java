package com.jobprocessingengine.job;

import java.time.Instant;
import java.util.UUID;

/**
 * Immutable data model representing a unit of work in the system.
 *
 * <p>Responsibilities:
 * <ul>
 *   <li>Hold job identity, type, payload, and submission timestamp</li>
 *   <li>Provide no business logic — it is a pure data carrier (value object)</li>
 * </ul>
 *
 * <p>Status tracking is intentionally excluded from this class to keep it
 * immutable; status is managed exclusively by {@link com.jobprocessingengine.store.JobStore}.
 */
public class Job {

    /** Unique identifier for this job instance. */
    private final String id;

    /** The category of work this job represents. */
    private final JobType type;

    /** Arbitrary payload describing the work to be done. */
    private final String payload;

    /** Timestamp at which the job was submitted to the engine. */
    private final Instant submittedAt;

    /**
     * Creates a new Job with a generated ID and the current submission time.
     *
     * @param type    the type of job
     * @param payload the data describing the work to be performed
     */
    public Job(JobType type, String payload) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.payload = payload;
        this.submittedAt = Instant.now();
    }

    /** @return the unique identifier of this job */
    public String getId() {
        return id;
    }

    /** @return the type of this job */
    public JobType getType() {
        return type;
    }

    /** @return the payload of this job */
    public String getPayload() {
        return payload;
    }

    /** @return the time at which this job was submitted */
    public Instant getSubmittedAt() {
        return submittedAt;
    }

    @Override
    public String toString() {
        return "Job{id='" + id + "', type=" + type + ", submittedAt=" + submittedAt + "}";
    }
}
