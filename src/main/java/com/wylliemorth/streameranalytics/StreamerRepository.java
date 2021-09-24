package com.wylliemorth.streameranalytics;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreamerRepository extends JpaRepository<Streamer, String> {
    Streamer findByName(String name);
    List<Streamer> findByStreamerType(StreamerType streamerType);
}
