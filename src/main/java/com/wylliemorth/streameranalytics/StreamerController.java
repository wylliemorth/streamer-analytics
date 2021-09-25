package com.wylliemorth.streameranalytics;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Date;
import java.util.List;

@RestController
public class StreamerController {

    private final StreamerRepository streamerRepository;

    public StreamerController(StreamerRepository repository) {
        this.streamerRepository = repository;
    }

    @GetMapping(value = "/streamers/{name}")
    public EntityModel<Streamer> one(@PathVariable String name) {
        Streamer streamer = this.streamerRepository.findById(name).orElseThrow(() -> new StreamerNotFoundException(name));

        return EntityModel.of(streamer, linkTo(methodOn(StreamerController.class).one(name)).withSelfRel(),
                linkTo(methodOn(StreamerController.class).all()).withRel("streamers"));
    }

    @GetMapping(value = "/streamers")
    public CollectionModel<EntityModel<Streamer>> all() {
         List<Streamer> streamers = this.streamerRepository.findAll();

    }

    @PutMapping(value = "/streamers/{name}")
    public Streamer replaceStreamer(@RequestBody Streamer newStreamer, @PathVariable String name) {
        return streamerRepository.findById(name)
                .map(streamer -> {
                    streamer.setViewCount(newStreamer.getViewCount());
                    streamer.setJoined(newStreamer.getJoined());
                    streamer.setStreamerType(newStreamer.getStreamerType());
                    streamer.setType(newStreamer.getType());
                    streamer.setProfileImage(newStreamer.getProfileImage());
                    return streamerRepository.save(streamer);
                }).orElseThrow(() -> new StreamerNotFoundException(name));
    }
}
