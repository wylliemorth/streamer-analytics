package com.wylliemorth.streameranalytics.streamer;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StreamerType {
    PARTNER,
    AFFILIATE,
    NONE;

    @JsonCreator
    public static StreamerType forName(String name) {
        for(StreamerType type: values()) {
            if(type.name().equals(name)) {
                return type;
            }
        }

        return NONE;
    }
}
