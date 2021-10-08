package com.wylliemorth.streameranalytics.requests;

import com.wylliemorth.streameranalytics.requests.dtos.AuthTwitchResponse;
import com.wylliemorth.streameranalytics.stream.Stream;
import com.wylliemorth.streameranalytics.streamer.Streamer;
import com.wylliemorth.streameranalytics.requests.dtos.StreamTwitchResponse;
import com.wylliemorth.streameranalytics.requests.dtos.StreamerTwitchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class TwitchRestService {
    private final static String API_USERS = "https://api.twitch.tv/helix/users?login=stevenbh&login=esl_csgo";
    private final static String API_STREAMS = "https://api.twitch.tv/helix/streams?user_login=esl_csgo";
    private final static String API_AUTH = "https://id.twitch.tv/oauth2/token?client_id=%s&client_secret=%s&grant_type=client_credentials";
    private static final Logger log = LoggerFactory.getLogger(TwitchRestService.class);

    private final Environment environment;
    private final RestTemplate restTemplate;

    private String oauthToken;

    public TwitchRestService(Environment environment, RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        this.environment = environment;
        this.oauthToken = null;
    }

    private boolean twitchAuthRequest() {
        String clientId = this.environment.getProperty("twitch-client-id");
        String clientSecret = this.environment.getProperty("twitch-client-secret");
        String url = String.format(API_AUTH, clientId, clientSecret);
        HttpEntity<?> request = new HttpEntity<>(new HttpHeaders());

        log.info("REQUEST STARTED Getting new oauth token...");

        try {
            AuthTwitchResponse response = this.restTemplate.postForObject(url, request, AuthTwitchResponse.class);
            if (response == null) {
                log.error("Auth: response came null!");
                return false;
            }

            this.oauthToken = response.getAccessToken();
        } catch (HttpClientErrorException e) {
            log.error("HTTP Error while getting authentication token! {}", e.getMessage());
            return false;
        }

        log.info("REQUEST SUCCESS Getting new oauth token!");
        return true;
    }

    public List<Streamer> getStreamers() {
        if (this.oauthToken == null) {
            if (!this.twitchAuthRequest()) {
                log.error("Aborted fetching streamers!");
                return null;
            }
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + this.oauthToken);
            headers.set("Client-Id", environment.getProperty("twitch-client-id"));

            HttpEntity<?> request = new HttpEntity<>(headers);
            ResponseEntity<StreamerTwitchResponse> response = this.restTemplate.exchange(
                    API_USERS,
                    HttpMethod.GET,
                    request, StreamerTwitchResponse.class);

            StreamerTwitchResponse twitchResponse = response.getBody();
            if (twitchResponse == null) {
                log.error("Streamers response body came null!");
                return null;
            }

            return twitchResponse.getStreamers();
        } catch (HttpClientErrorException e) {
            // FIXME need to differentiate from authentication errors
            log.error("Error fetching streamer data! {}", e.getMessage());
            return null;
        }
    }

    public List<Stream> getActiveStreams() {
        if (this.oauthToken == null) {
            boolean success = this.twitchAuthRequest();
            if (!success) {
                log.error("Aborted fetching streams!");
                return null;
            }
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + this.oauthToken);
            headers.set("Client-Id", environment.getProperty("twitch-client-id"));

            HttpEntity<?> request = new HttpEntity<>(headers);
            ResponseEntity<StreamTwitchResponse> response = this.restTemplate.exchange(
                    API_STREAMS,
                    HttpMethod.GET,
                    request, StreamTwitchResponse.class);

            StreamTwitchResponse twitchResponse = response.getBody();
            if (twitchResponse == null) {
                log.error("Stream response body came null!");
                return null;
            }

            return twitchResponse.getStreams();
        } catch (HttpClientErrorException e) {
            log.error("Error fetching stream data! {}", e.getMessage());
            return null;
        }
    }
}