package com.wylliemorth.streameranalytics.streamer;

import com.wylliemorth.streameranalytics.streamer.Streamer;
import com.wylliemorth.streameranalytics.streamer.StreamerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreamerRepository extends JpaRepository<Streamer, String> {
    List<Streamer> findByStreamerType(StreamerType streamerType);
}
