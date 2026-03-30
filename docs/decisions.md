# Decisions

## Job Immutability
Decision: Job is immutable
Reason: Avoid shared mutable state issues
Tradeoff: Requires replacing object in store, risk of lost updates

## Processor Dispatch
Decision: Use supports() instead of if-else
Reason: Extensible and follows strategy pattern
Tradeoff: O(n) lookup per job