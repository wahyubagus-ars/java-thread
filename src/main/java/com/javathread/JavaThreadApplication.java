package com.javathread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaThreadApplication {

	public static void main(String[] args) {
		String thread = Thread.currentThread().getName();
		System.out.println(thread);
	}

}
