package com.wylliemorth.streameranalytics.streamer;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserType {
    STAFF,
    ADMIN,
    GLOBAL_MOD,
    NONE;

    @JsonCreator
    public static UserType forName(String name) {
        for(UserType type: values()) {
            if(type.name().equals(name)) {
                return type;
            }
        }

        return NONE;
    }
}
