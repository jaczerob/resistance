package dev.jaczerob.resistance.api.services;

import dev.jaczerob.resistance.api.exceptions.ResistanceException;
import dev.jaczerob.resistance.api.exceptions.ToonExistsException;
import dev.jaczerob.resistance.api.models.toons.Toon;
import dev.jaczerob.resistance.api.repositories.toons.ToonEntity;
import dev.jaczerob.resistance.api.repositories.toons.ToonRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ToonService {
    private final ToonRepository toonRepository;

    public ToonService(final ToonRepository toonRepository) {
        this.toonRepository = toonRepository;
    }

    public Toon createToon(final Toon toon) throws ResistanceException {
        try {
            final ToonEntity toonEntity = ToonEntity.fromToon(toon);
            this.toonRepository.save(toonEntity);
            return toon;
        } catch (final ConstraintViolationException exc) {
            throw new ToonExistsException(toon.id());
        }
    }

    public Optional<Toon> getToon(final UUID id) {
        return this.toonRepository.findById(id).map(ToonEntity::toToon);
    }

    public Set<Toon> getToons() {
        return this.toonRepository.findAll().stream().map(ToonEntity::toToon).collect(Collectors.toUnmodifiableSet());
    }
}
