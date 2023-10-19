package com.javathread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootTest
public class TimerTaskTest {

    @Test
    void timerTaskTest() throws InterruptedException {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("delay job");
            }
        };

        var timer = new Timer();
        timer.schedule(timerTask, 1000);

        Thread.sleep(3000);
    }

    @Test
    void timerTaskPeriodicTest() throws InterruptedException {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("delay job");
            }
        };

        var timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);

        Thread.sleep(3000);
    }
}
