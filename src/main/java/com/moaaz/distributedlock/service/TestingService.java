package com.moaaz.distributedlock.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestingService {


    private final Logger log = LoggerFactory.getLogger(TestingService.class);
    private final String TESTING_REDIS_KEY = "testing"; //fixme should be stored as env var.

    private final LockService lockService;


    public TestingService(LockService lockService) {
        this.lockService = lockService;
    }


    public void doYourMagic() {

        try {
            // 1. Wait until the key be unlocked.
            boolean lockId = lockService.isLocked(TESTING_REDIS_KEY);
            while (lockId) {
                lockId = lockService.isLocked(TESTING_REDIS_KEY);
            }
            // 2. Lock to make other instances wait.
            lockService.lock(TESTING_REDIS_KEY);
            // 3. Do what you want.
            log.info("Starting critical operation...");
            performCriticalOperation();
            log.info("Critical operation completed successfully");

        } catch (Exception e) {
            log.error("Error during critical operation", e);
            throw new RuntimeException("Operation failed", e);
        } finally {
            // to lock after finishing what you are doing.... or it will be removed after 30 seconds as mentioned in LockServiceImpl
            lockService.unlock(TESTING_REDIS_KEY);
        }
    }

    private void performCriticalOperation() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
