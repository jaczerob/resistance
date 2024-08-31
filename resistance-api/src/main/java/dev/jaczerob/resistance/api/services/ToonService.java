package dev.jaczerob.resistance.api.services;

import dev.jaczerob.resistance.api.models.toons.Toon;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ToonService {
    private final Set<Toon> toons = ConcurrentHashMap.newKeySet();

    public Toon createToon(final Toon toon) {
        this.toons.add(toon);
        return toon;
    }

    public Optional<Toon> getToon(final UUID id) {
        return this.toons.stream().filter(toon -> toon.id().equals(id)).findFirst();
    }

    public Set<Toon> getToons() {
        return Set.copyOf(this.toons);
    }
}
