package com.moaaz.distributedlock.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LockServiceImpl implements LockService {


    private final Logger log = LoggerFactory.getLogger(LockServiceImpl.class);

    private final String PREFIX_LOCK_KEY = "lock_";// key prefix for all the locks
    private static final int DEFAULT_LOCK_SECONDS = 5; // time to remove key from redis, to be marked as not blocked
    private final RedisTemplate<String, Boolean> redisTemplate;

    public LockServiceImpl(RedisTemplate<String, Boolean> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /**
     * It locks the key
     *
     * @param key - The lock key
     */
    @Override
    public void lock(String key) {
        String lockKey = PREFIX_LOCK_KEY + key;
        redisTemplate.opsForValue()
                .setIfAbsent(lockKey, true, DEFAULT_LOCK_SECONDS, TimeUnit.MINUTES);

    }

    /**
     * Checks if a key is actively locked (exists AND has value = true)
     * @param key The lock key (without prefix)
     * @return true if locked, false if available or expired
     */
    public boolean isLocked(String key) {
        String lockKey = PREFIX_LOCK_KEY + key;
        Boolean value = (Boolean) redisTemplate.opsForValue().get(lockKey);
        return Boolean.TRUE.equals(value);
    }

    @Override
    public void unlock(String key) {
        redisTemplate.delete(PREFIX_LOCK_KEY + key);
        log.info("Released lock for key: {}", key);
    }
}
