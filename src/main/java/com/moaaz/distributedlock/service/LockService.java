package com.moaaz.distributedlock.service;

public interface LockService {

    void lock(String key);

    boolean isLocked(String key);

    void unlock(String key);
}