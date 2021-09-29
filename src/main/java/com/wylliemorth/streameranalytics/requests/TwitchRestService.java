package com.wylliemorth.streameranalytics.requests;

import com.wylliemorth.streameranalytics.stream.Stream;
import com.wylliemorth.streameranalytics.streamer.Streamer;
import com.wylliemorth.streameranalytics.requests.dtos.StreamTwitchResponse;
import com.wylliemorth.streameranalytics.requests.dtos.StreamerTwitchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class TwitchRestService {
    private final RestTemplate restTemplate;
    private final static String API_USERS = "https://api.twitch.tv/helix/users?login=stevenbh&login=asmongold&login=jblow";
    private final static String API_STREAMS = "https://api.twitch.tv/helix/streams?user_login=esl_csgo";
    private static final Logger log = LoggerFactory.getLogger(TwitchRestService.class);

    public TwitchRestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Streamer> getStreamers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer <token>");
        headers.set("Client-id", "<client id>");

        try {
            HttpEntity<?> request = new HttpEntity<>(headers);

            ResponseEntity<StreamerTwitchResponse> response = this.restTemplate.exchange(
                    API_USERS,
                    HttpMethod.GET,
                    request, StreamerTwitchResponse.class);

            StreamerTwitchResponse twitchResponse = response.getBody();
            if (twitchResponse == null) {
                log.error("Response body came null!");
                return null;
            }

            return twitchResponse.getStreamers();
        } catch (HttpClientErrorException e) {
            log.error("Error fetching streamer data! {}", e.getMessage());
            return null;
        }
    }

    public List<Stream> getActiveStreams() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer <token>");
        headers.set("Client-Id", "<client id>");

        try {
            HttpEntity<?> request = new HttpEntity<>(headers);
            ResponseEntity<StreamTwitchResponse> response = this.restTemplate.exchange(
                    API_STREAMS,
                    HttpMethod.GET,
                    request, StreamTwitchResponse.class);

            StreamTwitchResponse twitchResponse = response.getBody();
            if (twitchResponse == null) {
                log.error("Response body came null!");
                return null;
            }

            return twitchResponse.getStreams();
        } catch (HttpClientErrorException e) {
            log.error("Error fetching stream data! {}", e.getMessage());
            return null;
        }
    }
}