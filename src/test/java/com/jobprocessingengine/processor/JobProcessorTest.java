package com.jobprocessingengine.processor;

import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link PrintJobProcessor} and {@link EmailJobProcessor}.
 */
class JobProcessorTest {

    @Test
    void printProcessorShouldSupportPrintJobs() {
        PrintJobProcessor processor = new PrintJobProcessor();
        Job printJob = new Job(JobType.PRINT, "payload");
        assertTrue(processor.supports(printJob));
    }

    @Test
    void printProcessorShouldNotSupportEmailJobs() {
        PrintJobProcessor processor = new PrintJobProcessor();
        Job emailJob = new Job(JobType.EMAIL, "payload");
        assertFalse(processor.supports(emailJob));
    }

    @Test
    void emailProcessorShouldSupportEmailJobs() {
        EmailJobProcessor processor = new EmailJobProcessor();
        Job emailJob = new Job(JobType.EMAIL, "test@example.com");
        assertTrue(processor.supports(emailJob));
    }

    @Test
    void emailProcessorShouldNotSupportPrintJobs() {
        EmailJobProcessor processor = new EmailJobProcessor();
        Job printJob = new Job(JobType.PRINT, "payload");
        assertFalse(processor.supports(printJob));
    }

    @Test
    void printProcessorShouldNotSupportNullJob() {
        PrintJobProcessor processor = new PrintJobProcessor();
        assertFalse(processor.supports(null));
    }

    @Test
    void emailProcessorShouldNotSupportNullJob() {
        EmailJobProcessor processor = new EmailJobProcessor();
        assertFalse(processor.supports(null));
    }
}
