package com.wylliemorth.streameranalytics.requests;

import com.wylliemorth.streameranalytics.Streamer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class RestService {
    private final RestTemplate restTemplate;
    private final static String API_USERS = "https://api.twitch.tv/helix/users?login=stevenbh?login=asmongold&login=jblow";
    private final static String API_STREAMS = "https://api.twitch.tv/helix/streams?user_login=";
    private static final Logger log = LoggerFactory.getLogger(RestService.class);

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Streamer> getStreams() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer token");
        headers.set("Client-id", "client id");

        try {
            HttpEntity request = new HttpEntity(headers);
            ResponseEntity<StreamerData> response = this.restTemplate.exchange(API_USERS, HttpMethod.GET, request, StreamerData.class);
            log.info("Response code: {}", response.getStatusCode());

            return Objects.requireNonNull(response.getBody()).getStreamers();
        } catch (HttpClientErrorException e) {
            log.error("Error fetching stream data! {}", e.getMessage());
            return null;
        }
    }
}