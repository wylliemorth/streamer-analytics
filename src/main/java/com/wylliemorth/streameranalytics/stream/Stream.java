package com.wylliemorth.streameranalytics.stream;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wylliemorth.streameranalytics.streamer.Streamer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Stream implements Serializable {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn
    @JsonProperty("user_name")
    private Streamer streamer;

    @ElementCollection
    private List<String> titles;

    @ElementCollection
    private List<String> games;

    private Date startedAt;
    private Integer averageViewCount;
    private Integer peakViewCount;

    @JsonProperty("view_count")
    private void unpackViewCounts(Integer viewCount) {
        // TODO: Calculate average...
        this.averageViewCount = viewCount;
        if (viewCount > this.peakViewCount) this.peakViewCount = viewCount;
    }

    @JsonProperty("title")
    private void unpackStreamTitle(String title) {
        if (!this.titles.contains(title)) {
            this.titles.add(title);
        }
    }

    @JsonProperty("game_name")
    private void unpackGameName(String gameName) {
        if (!this.games.contains(gameName)) {
            this.games.add(gameName);
        }
    }

    public Stream() {
        this.titles = new ArrayList<>();
        this.games = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Streamer getStreamer() {
        return streamer;
    }

    public void setStreamer(Streamer streamer) {
        this.streamer = streamer;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Integer getAverageViewCount() {
        return averageViewCount;
    }

    public void setAverageViewCount(Integer averageViewCount) {
        this.averageViewCount = averageViewCount;
    }

    public Integer getPeakViewCount() {
        return peakViewCount;
    }

    public void setPeakViewCount(Integer peakViewCount) {
        this.peakViewCount = peakViewCount;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getGames() {
        return games;
    }

    public void setGames(List<String> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Stream{" +
                "id=" + id +
                ", streamer=" + streamer +
                ", titles=" + titles +
                ", games=" + games +
                ", startedAt=" + startedAt +
                ", averageViewCount=" + averageViewCount +
                ", peakViewCount=" + peakViewCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stream stream = (Stream) o;
        return id.equals(stream.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
