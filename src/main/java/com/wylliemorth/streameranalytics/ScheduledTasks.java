package com.wylliemorth.streameranalytics;

import com.wylliemorth.streameranalytics.stream.Stream;
import com.wylliemorth.streameranalytics.streamer.Streamer;
import com.wylliemorth.streameranalytics.requests.TwitchRestService;
import com.wylliemorth.streameranalytics.streamer.StreamerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final int FETCH_STREAMS_RATE = 15000; // FIXME: change to 1 minute
    private static final int FETCH_USERS_RATE = 60000; // FIXME: change to 60 minutes
    private final TwitchRestService restService;

    public ScheduledTasks(TwitchRestService restService) {
        this.restService = restService;
    }

    @Scheduled(fixedRate = FETCH_STREAMS_RATE)
    public void fetchStreams() {
        log.info("{} FETCH stream data...", dateFormat.format(new Date()));

        List<Stream> streams = restService.getActiveStreams();
        if (streams == null) {
            return;
        }

        log.info("{} FINISHED fetching stream data...", dateFormat.format(new Date()));
    }

    @Scheduled(fixedRate = FETCH_USERS_RATE)
    public void fetchUsers() {
        log.info("{} FETCH streamer data...", dateFormat.format(new Date()));

        List<Streamer> streamers = restService.getStreamers();
        if (streamers == null) {
            return;
        }

        log.info("{} FINISHED fetching streamer data...", dateFormat.format(new Date()));
    }
}
