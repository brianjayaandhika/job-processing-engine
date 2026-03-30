# System Overview

This project is a concurrent job processing engine built in plain Java.

Core flow:
Submit Job → Store → Queue → Worker → Processor → Store update

Key constraints:
- Job is a value object (no business logic)
- JobStore is the single source of truth
- Queue is abstracted (BlockingQueue internally)
- Worker handles execution, not business logic
- Processor encapsulates job-specific behavior

Concurrency concerns:
- Shared mutable state in JobStore
- Worker threads consuming concurrently
- Retry logic must not cause duplication
- State transitions must be valid and controlled

Current phase:
Phase 1 — Core system (no Spring, no persistence)