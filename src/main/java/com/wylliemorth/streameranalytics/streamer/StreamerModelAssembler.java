package com.wylliemorth.streameranalytics.streamer;

import com.wylliemorth.streameranalytics.streamer.Streamer;
import com.wylliemorth.streameranalytics.streamer.StreamerController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class StreamerModelAssembler implements RepresentationModelAssembler<Streamer, EntityModel<Streamer>> {

    @Override
    public EntityModel<Streamer> toModel(Streamer streamer) {
        return EntityModel.of(
                streamer,
                linkTo(methodOn(StreamerController.class).one(streamer.getName())).withSelfRel(),
                linkTo(methodOn(StreamerController.class).all()).withRel("streamers")
        );
    }
}
