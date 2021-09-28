package com.wylliemorth.streameranalytics.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wylliemorth.streameranalytics.Streamer;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class StreamerData implements Serializable {
    private List<Streamer> streamers;

    @JsonProperty("data")
    private void unpackStreamersFromData(List<Streamer> streamers) {
        this.streamers = streamers;
    }

    public List<Streamer> getStreamers() {
        return streamers;
    }

    public void setStreamers(List<Streamer> streamers) {
        this.streamers = streamers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Streamer s : this.streamers) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

}
