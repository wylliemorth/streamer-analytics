package com.wylliemorth.streameranalytics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class StreamerController {

    @GetMapping(value = "/streamer/{name}")
    public Streamer getStreamer(@PathVariable String name) {
        return new Streamer(name, StreamerType.NONE, null, UserType.NONE, 420, new Date());
    }
}
