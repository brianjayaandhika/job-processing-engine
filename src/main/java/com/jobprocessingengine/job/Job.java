package com.jobprocessingengine.job;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;


public class Job {

    private final String id;

    private final JobType type;

    private final String payload;

    private final Instant createdAt;

    public Job(JobType type, String payload, Instant createdAt) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.payload = payload;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public JobType getType() {
        return type;
    }

    public String getPayload() {
        return payload;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", payload='" + payload + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
