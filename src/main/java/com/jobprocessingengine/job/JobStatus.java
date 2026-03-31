package com.jobprocessingengine.job;


public enum JobStatus {
    PENDING, RUNNING, SUCCESS, FAILED;

    // Allowed Transition PENDING -> RUNNING -> SUCCESS -> FAILED
}
