package com.wylliemorth.streameranalytics.streamer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wylliemorth.streameranalytics.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Streamer implements Serializable {

    @JsonProperty("display_name")
    private @Id String name;

    @JsonProperty("broadcaster_type")
    private StreamerType streamerType;

    @JsonProperty("profile_image_url")
    private String profileImage;

    private UserType type;
    private Integer viewCount;
    private Date joined;
    private String description;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Stream> streams;

    public Streamer() {}

    public Streamer(String name) {
        this.name = name;
    }

    public Streamer(String name, StreamerType streamerType, String profileImage, UserType type, Integer viewCount, Date joined, String description, List<Stream> streams) {
        this.name = name;
        this.streamerType = streamerType;
        this.profileImage = profileImage;
        this.type = type;
        this.viewCount = viewCount;
        this.joined = joined;
        this.description = description;
        this.streams = streams;
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

    @Override
    public String toString() {
        return "Streamer{" +
                "name='" + name + '\'' +
                ", streamerType=" + streamerType +
                ", profileImage='" + profileImage + '\'' +
                ", type=" + type +
                ", viewCount=" + viewCount +
                ", joined=" + joined +
                ", description=" + description +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    public void setStreams(List<Stream> streams) {
        this.streams = streams;
    }
}
