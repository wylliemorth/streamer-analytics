package com.wylliemorth.streameranalytics;

import com.wylliemorth.streameranalytics.requests.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final int FETCH_STREAM_RATE = 5000;
    private final StreamerRepository streamerRepository;
    private final RestService restService;

    public ScheduledTasks(StreamerRepository streamerRepository, RestService restService) {
        this.streamerRepository = streamerRepository;
        this.restService = restService;
    }

    @Scheduled(fixedRate = FETCH_STREAM_RATE)
    public void fetchStreams() {
        log.info("{} Fetching stream data...", dateFormat.format(new Date()));

        // GET LIST OF STREAMERS
        List<Streamer> streamers = streamerRepository.findAll();
        // Take into account stream id
        List<Streamer> streams = restService.getStreams();
        if (streams != null) {
            log.info("Doing things...");
        }

        log.info("{} Finished fetching stream data...", dateFormat.format(new Date()));
    }
}
