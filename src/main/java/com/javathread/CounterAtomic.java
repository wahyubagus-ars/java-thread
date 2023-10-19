package com.javathread;

import java.util.concurrent.atomic.AtomicLong;

public class CounterAtomic {

    private AtomicLong counter = new AtomicLong();

    /**
     * This code create the race condition
     *
    public void increment() {
        counter++;
    }
     *
     */

    /**
     * this code is syncronized auto handle by java
     */
    public synchronized void increment() {
        this.counter.getAndIncrement();
    }

    public Long getValue() {
        return this.counter.get();
    }

}
