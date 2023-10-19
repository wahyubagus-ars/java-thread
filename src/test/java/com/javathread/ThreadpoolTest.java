package com.javathread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@SpringBootTest
public class ThreadpoolTest {

    @Test
    void createThreadpool() throws InterruptedException {
        var minthread = 10;
        var maxthread = 100;
        var alive = 1;
        var time = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(100);

        var threadpool = new ThreadPoolExecutor(minthread, maxthread, alive, time, queue);

        threadpool.execute(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Thread Name: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(6000);
    }

    @Test
    void shutdownThreadpool() throws InterruptedException {
        var minthread = 10;
        var maxthread = 100;
        var alive = 1;
        var time = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(1000);
        var rejectedHandler = new LogRejectionExecutionHandler();

        var threadpool = new ThreadPoolExecutor(minthread, maxthread, alive, time, queue, rejectedHandler);

        for (var i = 0; i < 2000; i++) {
            final var task = i;
            threadpool.execute(() -> {
                try {
                    Thread.sleep(5000);
                    System.out.println("Task:" + task + " Thread Name: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(7000);
        threadpool.shutdown();
    }

    public static class LogRejectionExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            System.out.println("Task: " + runnable + "rejected");
        }
    }
}
