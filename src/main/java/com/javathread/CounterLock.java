package com.javathread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterLock {

    private Long value = 0L;

    final private Lock lock = new ReentrantLock();

    /**
     * ReadWriteLock have 2 option to lock read or write operation
     */
    final private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void increment() {
        try {
            lock.lock();
            value++;
        } finally {
            lock.unlock();
        }
    }

    public Long getValue() {
        return this.value;
    }

}
