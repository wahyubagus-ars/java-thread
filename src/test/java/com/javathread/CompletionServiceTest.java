package com.javathread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.concurrent.*;

@SpringBootTest
public class CompletionServiceTest {

    private Random random = new Random();

    @Test
    void test() throws InterruptedException {
        var executor = Executors.newFixedThreadPool(10);

        // Completion service act as bridge to transfer the data
        var completionService = new ExecutorCompletionService<String>(executor);

        // Submit task
        Executors.newSingleThreadExecutor().execute(() -> {
            for (int i = 0; i < 100; i++) {
                final var  index = i;
                completionService.submit(() -> {
                    Thread.sleep(random.nextInt(2000));
                    return "Task-" + index;
                });
            }
        });

        // POLL / retrieving the data through completion service
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {
                try {
                    Future<String> future = completionService.poll(5, TimeUnit.MINUTES);
                    if (future == null) {
                        break;
                    } else {
                        System.out.println(future.get());
                    }
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                    break;
                }

            }
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

}
