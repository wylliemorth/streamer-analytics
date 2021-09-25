package com.wylliemorth.streameranalytics;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Streamer {

    private @Id String name;
    private StreamerType streamerType;
    private String profileImage;
    private UserType type;
    private Integer viewCount;
    private Date joined;

    public Streamer() {}

    public Streamer(String name) {
        this.name = name;
    }

    public Streamer(String name, StreamerType streamerType, String profileImage, UserType type, Integer viewCount, Date joined) {
        this.name = name;
        this.streamerType = streamerType;
        this.profileImage = profileImage;
        this.type = type;
        this.viewCount = viewCount;
        this.joined = joined;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StreamerType getStreamerType() {
        return streamerType;
    }

    public void setStreamerType(StreamerType streamerType) {
        this.streamerType = streamerType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Date getJoined() {
        return joined;
    }

    public void setJoined(Date joined) {
        this.joined = joined;
    }
}
