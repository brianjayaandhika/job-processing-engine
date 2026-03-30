# Failure Scenarios

- Worker crashes during processing
- Job processed twice
- Job stuck in RUNNING forever
- Queue grows faster than processing
- Retry loop causes infinite requeue
- Job state updated concurrently (race condition)