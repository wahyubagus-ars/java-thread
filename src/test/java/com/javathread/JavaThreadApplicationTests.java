package com.javathread;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaThreadApplicationTests {

	@Test
	void getMainThread() {
		String thread = Thread.currentThread().getName();
		System.out.println(thread);
	}

	@Test
	void createThread() {
		Runnable runnable = () -> {
			System.out.println("Hello from thread: " + Thread.currentThread().getName());
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}

	@Test
	void threadSleep() throws InterruptedException {
		Runnable runnable = () -> {
			try {
				Thread.sleep(2_000L);
				System.out.println("Hello from thread: " + Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();

		System.out.println("Program finish");

		Thread.sleep(3_00L);
	}

	/**
	 *
	 * Thread join is blocking the thread and waiting until it done, better option than thread.Sleep()
	 * @throws InterruptedException
	 */
	@Test
	void threadJoin() throws InterruptedException {
		Runnable runnable = () -> {
			try {
				Thread.sleep(2_000L);
				System.out.println("Hello from thread: " + Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
		thread.join();

		System.out.println("Program finish");
	}

	@Test
	void threadInterrupt() throws InterruptedException {
		Runnable runnable = () -> {
			for (int i = 0; i < 10; i ++){
				System.out.println("Runnable : " + i);
				try {
					Thread.sleep(1_000);
				} catch (InterruptedException e) {
					return;
				}
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
		Thread.sleep(5_000);
		thread.interrupt();

		System.out.println("Awaiting done");
		thread.join();
		System.out.println("Program finish");

		System.out.println("Program finish");
	}

	@Test
	void threadCustomiseName() {
		Runnable runnable = () -> {
			System.out.println("Hello from thread: " + Thread.currentThread().getName());
		};

		Thread thread = new Thread(runnable);
		thread.setName("thread-example-customise-name");
		thread.start();
	}

	@Test
	void threadState() throws InterruptedException {
		Thread thread = new Thread(() -> {
			System.out.println(Thread.currentThread().getState()); // the state is 'runnable'
		});

		System.out.println(thread.getState()); // the state is 'new'

		thread.start();
		thread.join();

		System.out.println(thread.getState()); // the state is 'terminated'
	}
}
