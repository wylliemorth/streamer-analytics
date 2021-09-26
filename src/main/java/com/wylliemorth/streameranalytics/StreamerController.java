package com.wylliemorth.streameranalytics;

import org.hibernate.EntityMode;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StreamerController {

    private final StreamerRepository streamerRepository;
    private final StreamerModelAssembler assembler;

    public StreamerController(StreamerRepository repository, StreamerModelAssembler modelAssembler) {
        this.streamerRepository = repository;
        this.assembler = modelAssembler;
    }

    @GetMapping(value = "/streamers/{name}")
    public EntityModel<Streamer> one(@PathVariable String name) {
        Streamer streamer = this.streamerRepository.findById(name).orElseThrow(() -> new StreamerNotFoundException(name));

        return assembler.toModel(streamer);
    }

    @GetMapping(value = "/streamers")
    public CollectionModel<EntityModel<Streamer>> all() {
         List<EntityModel<Streamer>> streamers = this.streamerRepository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());

         return CollectionModel.of(
                 streamers,
                 linkTo(methodOn(StreamerController.class).all()).withSelfRel()
         );
    }

    @PutMapping(value = "/streamers/{name}")
    public ResponseEntity<?> replaceStreamer(@RequestBody Streamer newStreamer, @PathVariable String name) {
        Streamer updatedStreamer = streamerRepository.findById(name)
                .map(streamer -> {
                    streamer.setViewCount(newStreamer.getViewCount());
                    streamer.setJoined(newStreamer.getJoined());
                    streamer.setStreamerType(newStreamer.getStreamerType());
                    streamer.setType(newStreamer.getType());
                    streamer.setProfileImage(newStreamer.getProfileImage());
                    return streamerRepository.save(streamer);
                }).orElseThrow(() -> new StreamerNotFoundException(name));
        EntityModel<Streamer> streamerEntityModel = assembler.toModel(updatedStreamer);
        return ResponseEntity
                .created(streamerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(streamerEntityModel);
    }

    @PostMapping(value = "/streamers")
    public ResponseEntity<?> newStreamer(@RequestBody Streamer newStreamer) {
        EntityModel<Streamer> streamerEntityModel = assembler.toModel(streamerRepository.save(newStreamer));
        return ResponseEntity
                .created(streamerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(streamerEntityModel);
    }

    @DeleteMapping(value = "/streamers/{name}")
    public ResponseEntity<?> deleteStreamer(@PathVariable String name) {
        streamerRepository.deleteById(name);
        return ResponseEntity.noContent().build();
    }
}
