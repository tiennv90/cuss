package com.kiosk.cuss.service.flight;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class FlightLockManager {
    private final ConcurrentHashMap<Long, ReentrantLock> locks = new ConcurrentHashMap<>();

    public ReentrantLock lockForFlight(Long flightId) {
        return locks.computeIfAbsent(flightId, k -> new ReentrantLock());
    }

    public ReentrantLock getLock(Long flightId) {
        return lockForFlight(flightId);
    }
}
