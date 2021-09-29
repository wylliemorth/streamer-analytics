package com.wylliemorth.streameranalytics.streamer;

public class StreamerNotFoundException extends RuntimeException {
    public StreamerNotFoundException(String id) {
        super("Could not find streamer " + id);
    }
}
