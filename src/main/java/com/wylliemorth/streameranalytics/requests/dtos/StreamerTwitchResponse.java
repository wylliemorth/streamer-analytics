package com.wylliemorth.streameranalytics.requests.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wylliemorth.streameranalytics.streamer.Streamer;

import java.util.ArrayList;
import java.util.List;

public class StreamerTwitchResponse {
    private List<Streamer> streamers;

    @JsonProperty("data")
    private void unpackStreamers(List<Streamer> streamers) {
        this.streamers = streamers;
    }

    public StreamerTwitchResponse() {
        this.streamers = new ArrayList<>();
    }

    public List<Streamer> getStreamers() {
        return streamers;
    }

    public void setStreamers(List<Streamer> streamers) {
        this.streamers = streamers;
    }
}
