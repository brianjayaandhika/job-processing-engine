package com.jobprocessingengine.queue;

import com.jobprocessingengine.job.Job;
import com.jobprocessingengine.job.JobType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link InMemoryJobQueue}.
 */
class InMemoryJobQueueTest {

    @Test
    void newQueueShouldBeEmpty() {
        InMemoryJobQueue queue = new InMemoryJobQueue();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void enqueueShouldIncreaseSize() throws InterruptedException {
        InMemoryJobQueue queue = new InMemoryJobQueue();
        queue.enqueue(new Job(JobType.PRINT, "payload"));
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    void dequeueShouldReturnEnqueuedJobInOrder() throws InterruptedException {
        InMemoryJobQueue queue = new InMemoryJobQueue();
        Job job1 = new Job(JobType.PRINT, "first");
        Job job2 = new Job(JobType.EMAIL, "second");
        queue.enqueue(job1);
        queue.enqueue(job2);

        assertEquals(job1.getId(), queue.dequeue().getId());
        assertEquals(job2.getId(), queue.dequeue().getId());
    }
}
