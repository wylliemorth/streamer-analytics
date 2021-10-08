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
    private static final int FETCH_USERS_RATE = 55000; // FIXME: change to 60 minutes
    private final TwitchRestService restService;

    private List<Stream> activeStreams;

    public ScheduledTasks(TwitchRestService restService) {
        this.restService = restService;
        this.activeStreams = new ArrayList<>();
    }

    @Scheduled(fixedRate = FETCH_STREAMS_RATE)
    public void fetchStreams() {
        log.info("{} Fetching stream data...", dateFormat.format(new Date()));

        List<Stream> streams = restService.getActiveStreams();
        if (streams == null) {
            return;
        }

        for (Stream s: streams){
            log.info(s.toString());
        }

        log.info("{} Finished fetching stream data...", dateFormat.format(new Date()));
    }

    @Scheduled(fixedRate = FETCH_USERS_RATE)
    public void fetchUsers() {
        List<Streamer> streamers = restService.getStreamers();
        if (streamers != null) {
            for (Streamer s : streamers) {
                log.info(s.toString());
            }
        }
    }
}
