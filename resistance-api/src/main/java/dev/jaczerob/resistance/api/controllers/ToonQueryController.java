package dev.jaczerob.resistance.api.controllers;

import dev.jaczerob.resistance.api.models.toons.Toon;
import dev.jaczerob.resistance.api.services.ToonService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Controller
public class ToonQueryController {
    private final ToonService toonService;

    public ToonQueryController(final ToonService toonService) {
        this.toonService = toonService;
    }

    @QueryMapping
    public Set<Toon> toons() {
        return this.toonService.getToons();
    }

    @QueryMapping
    public Optional<Toon> toonById(final @Argument UUID id) {
        return this.toonService.getToon(id);
    }
}
