Distributed Locking with Redis
Overview
This solution provides a simple mechanism to coordinate access to shared resources across multiple application instances using Redis as a distributed lock manager. It ensures only one instance can execute a critical section of code at any given time, preventing race conditions in distributed environments.

Key Features
Cross-instance coordination - Prevents concurrent execution across multiple servers/containers

Automatic expiration - Built-in safety mechanism to prevent deadlocks

Simple integration - Easy to add to existing applications

Lightweight - Minimal performance overhead

How It Works
Lock Acquisition: An instance requests a lock for a specific resource

Exclusive Access: While locked, other instances wait

Automatic Release: Locks expire after a configurable timeout

Resource Access: Safe execution of protected operations

Implementation Details
Uses Redis SETNX operation for atomic lock acquisition

Implements lock prefixing to avoid key collisions

Provides both blocking and non-blocking lock checks

Includes automatic cleanup via Redis key expiration

Ideal Use Cases
Database migrations

Scheduled job coordination

Resource-intensive operations

Critical section protection

Requirements
Redis server (v5.0+ recommended)

Spring Boot application

Redis client dependencies

Benefits
Eliminates race conditions in distributed systems

Maintains data consistency

Simple to implement and understand

No single point of failure (when using Redis Cluster)
