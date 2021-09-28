package com.wylliemorth.streameranalytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(StreamerRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Streamer("HazyAlex", StreamerType.NONE, "", UserType.NONE, 100, new Date(), "Hello")));
            log.info("Preloading " + repository.save(new Streamer("Someone", StreamerType.NONE, "", UserType.NONE, 420, new Date(), "Goodbye")));
        };
    }
}