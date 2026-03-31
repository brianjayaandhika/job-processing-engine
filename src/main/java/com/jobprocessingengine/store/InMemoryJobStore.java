package com.jobprocessingengine.store;

import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobStatus;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryJobStore implements JobStore {

    @Override
    public boolean save(Job job) {
        return false;
    }

    @Override
    public Job findById(String id) {
        return null;
    }

    @Override
    public JobStatus getStatus(String id) {
        return null;
    }

    @Override
    public boolean updateStatus(String id, JobStatus newStatus) {
        return false;
    }
}
