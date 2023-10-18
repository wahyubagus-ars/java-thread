package com.javathread;

public class Counter {

    private Long counter = 0L;

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
        counter++;
    }

    /**
     * this code is syncronized manually by us that define which line code would to lock
     */
    public void incrementSyncManual() {
        synchronized (this) {
            counter++;
        }
    }

    public Long getValue() {
        return this.counter;
    }

}
