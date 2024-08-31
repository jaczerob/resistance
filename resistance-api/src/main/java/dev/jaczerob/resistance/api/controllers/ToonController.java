package dev.jaczerob.resistance.api.controllers;

import dev.jaczerob.resistance.api.models.requests.CreateToonRequest;
import dev.jaczerob.resistance.api.models.toons.Toon;
import dev.jaczerob.resistance.api.services.ToonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/toons")
public class ToonController {
    private final ToonService toonService;

    public ToonController(final ToonService toonService) {
        this.toonService = toonService;
    }

    @PostMapping
    public ResponseEntity<Toon> createToon(final @RequestBody CreateToonRequest createToonRequest) {
        final Toon toon = new Toon(
                UUID.randomUUID(),
                createToonRequest.name(),
                createToonRequest.laff(),
                createToonRequest.species(),
                createToonRequest.toonUpGag(),
                createToonRequest.trapGag(),
                createToonRequest.lureGag(),
                createToonRequest.soundGag(),
                createToonRequest.throwGag(),
                createToonRequest.squirtGag(),
                createToonRequest.dropGag()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(this.toonService.createToon(toon));
    }
}
