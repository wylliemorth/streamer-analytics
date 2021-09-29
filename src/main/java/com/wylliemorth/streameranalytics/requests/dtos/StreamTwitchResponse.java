package com.wylliemorth.streameranalytics.requests.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wylliemorth.streameranalytics.stream.Stream;

import java.util.ArrayList;
import java.util.List;

public class StreamTwitchResponse {
    private List<Stream> streams;

    @JsonProperty("data")
    private void unpackStreams(List<Stream> streams) {
        this.streams = streams;
    }

    public StreamTwitchResponse() {
        this.streams = new ArrayList<>();
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }
}
