package com.wylliemorth.streameranalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StreamerAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamerAnalyticsApplication.class, args);
	}

}
