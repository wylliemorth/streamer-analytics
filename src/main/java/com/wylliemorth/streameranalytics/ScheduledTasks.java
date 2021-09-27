package com.wylliemorth.streameranalytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final int FETCH_STREAM_RATE = 60 * 1000;

    @Scheduled(fixedRate = FETCH_STREAM_RATE)
    public void fetchStreams() {
        log.info("{} Fetching stream data...", dateFormat.format(new Date()));

        // GET LIST OF STREAMERS

        // Take into account stream id



        log.info("{} Finished fetching stream data...", dateFormat.format(new Date()));
    }
}
