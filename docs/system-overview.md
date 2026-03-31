# System Overview

## Project Definition

This project is a **single-node concurrent job processing engine** built in plain Java.

The system accepts jobs, queues them, processes them concurrently using worker threads, and tracks job state safely.

This project is designed to **master Java concurrency fundamentals**, not just build a working system.

---

## Core Flow

Submit Job
→ Store (persist initial state)
→ Queue (enqueue job)
→ Worker (consume job)
→ Processor (execute logic)
→ Store (update state)

---

## Core Components

### 1. Job

* Immutable value object
* Contains: id, type, payload, timestamps
* Contains **no business logic and no status**

---

### 2. JobStore

* Single source of truth for job state
* Responsible for:

    * storing jobs
    * tracking job status
    * enforcing valid state transitions
* Must guarantee **thread-safe and atomic updates**

---

### 3. JobQueue

* Abstraction over a blocking queue
* Responsible for:

    * coordinating producers and consumers
    * ensuring safe job handoff between threads

---

### 4. Worker

* Continuously consumes jobs from queue
* Delegates execution to appropriate JobProcessor
* Handles:

    * state transitions (via JobStore)
    * error handling
    * retry triggering (if applicable)

---

### 5. JobProcessor

* Encapsulates job-specific logic
* Selected via strategy pattern (`supports(Job)`)
* Must not manage threading or shared state

---

### 6. Coordinator

* Entry point of the system
* Responsible for:

    * job submission
    * wiring queue + store
    * lifecycle management (start / shutdown)

---

## Key Design Constraints

* Plain Java only (no Spring, no frameworks)
* Concurrency correctness over convenience
* No hidden shared state between components
* Job must remain immutable
* JobStore must control all state transitions
* Worker must not contain business logic
* Queue must not be accessed directly outside its abstraction

---

## Concurrency Model

* Multiple producers (job submission)
* Multiple consumers (workers)
* Shared mutable state exists **only inside JobStore**
* All updates to shared state must be:

    * atomic
    * validated
    * thread-safe

---

## State Management Rules

* Job status is NOT stored inside Job
* Status is managed exclusively by JobStore
* Valid transitions must be enforced

Allowed transitions:

* PENDING → RUNNING
* RUNNING → SUCCESS
* RUNNING → FAILED

Invalid transitions must be rejected.

---

## Failure Scenarios (must be handled or acknowledged)

* Worker crashes during processing
* Job processed more than once
* Job stuck in RUNNING state
* Retry loop causes infinite reprocessing
* Queue grows faster than processing (no backpressure yet)
* Concurrent updates cause inconsistent state

---

## Current Phase

## Phase 1 — Core System (No Spring, No Persistence)

### Goal

Build a correct, in-memory concurrent processing engine with:

* safe job lifecycle management
* correct concurrency handling
* deterministic state transitions

---

### Constraints

* No database
* No REST API
* No distributed system
* No frameworks
* No shortcuts (e.g. no Lombok hiding logic)

---

### Implementation Steps

#### Step 1 — Job + JobStore (FOUNDATION)

* Define Job as immutable object
* Implement JobStore with:

    * thread-safe storage
    * atomic status updates
    * enforced state transitions

Focus:

* atomic operations (`ConcurrentHashMap.compute`)
* preventing race conditions
* preventing invalid transitions

---

#### Step 2 — JobQueue

* Implement queue abstraction
* Backed by `BlockingQueue`
* Must support:

    * safe concurrent enqueue/dequeue
    * blocking consumption

Focus:

* blocking vs non-blocking behavior
* avoiding busy waiting

---

#### Step 3 — Worker (CRITICAL)

* Implement worker loop
* Responsibilities:

    * dequeue job
    * update state → RUNNING
    * execute processor
    * update state → SUCCESS / FAILED

Focus:

* thread lifecycle
* interruption handling
* exception handling
* avoiding stuck threads

---

#### Step 4 — WorkerPool

* Manage multiple workers
* Responsibilities:

    * create threads
    * start execution
    * graceful shutdown

Focus:

* thread control
* avoiding orphan threads
* proper shutdown

---

#### Step 5 — Processor Layer

* Implement at least 2 processors
* Use strategy pattern (no if/else)

Focus:

* clean separation of logic
* extensibility

---

#### Step 6 — Coordinator

* Entry point of system
* Responsibilities:

    * submit jobs
    * store + enqueue
    * start/stop system

Focus:

* orchestration without becoming god class

---

#### Step 7 — Integration (Main)

* Wire everything together
* Simulate multiple job submissions

Focus:

* system behavior under concurrency

---

## Acceptance Criteria (Phase 1)

The system is considered correct if:

* Jobs are processed concurrently
* No job is lost
* No job is processed more than once (within design limits)
* State transitions are always valid
* System does not hang during shutdown
* Failures are handled deterministically

---

## Stop & Think Checkpoints

Before moving between steps, you must answer:

* Who owns state transitions?
* What prevents race conditions?
* What happens if two threads update the same job?
* Can this system break under load?

---

## Final Note

If something “just works” without you understanding why, assume it is wrong and investigate further.
