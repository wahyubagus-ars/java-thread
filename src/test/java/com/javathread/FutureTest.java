package com.javathread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class FutureTest {


    @Test
    void createFutureTest() throws ExecutionException, InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "hi";
        };

        Future<String> future = executor.submit(callable);

        String value = future.get();
        System.out.println(value);
    }

    @Test
    void createFutureIsCancelledTest() throws ExecutionException, InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "hi";
        };

        Future<String> future = executor.submit(callable);

        future.cancel(true);

        System.out.println(future.isCancelled());
        String value = future.get();
        System.out.println(value);
    }

    @Test
    void invokeAllCallable() throws InterruptedException, ExecutionException {
        var executor = Executors.newFixedThreadPool(10);
        List<Callable<String>> callableList = IntStream.range(1, 11).mapToObj(operand -> (Callable<String>) () -> {
            Thread.sleep(1000);
            return String.valueOf(operand);
        }).collect(Collectors.toList());

        List<Future<String>> futureList = executor.invokeAll(callableList);

        for (Future<String> future : futureList) {
            System.out.println(future.get());
        }
    }
}
