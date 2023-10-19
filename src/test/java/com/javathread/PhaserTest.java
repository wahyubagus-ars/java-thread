package com.javathread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class PhaserTest {

    @Test
    void phaserTest() throws InterruptedException {
        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(10);

        phaser.bulkRegister(5);

        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("start task");
                    Thread.sleep(2000);
                    System.out.println("End task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    phaser.arrive();
                }
            });
        }

        executor.execute(() -> {
            phaser.awaitAdvance(1);
            System.out.println("All item done");
        });

        executor.awaitTermination(1, TimeUnit.DAYS);
    }

}
