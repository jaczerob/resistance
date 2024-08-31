package dev.jaczerob.resistance.api.models.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.jaczerob.resistance.api.models.gags.*;
import dev.jaczerob.resistance.api.models.toons.Species;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateToonRequest(
        String name,
        int laff,
        Species species,

        ToonUpGag toonUpGag,
        TrapGag trapGag,
        LureGag lureGag,
        SoundGag soundGag,
        ThrowGag throwGag,
        SquirtGag squirtGag,
        DropGag dropGag
) {
}
