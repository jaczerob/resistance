package dev.jaczerob.resistance.api.exceptions;

import dev.jaczerob.resistance.api.models.toons.Toon;

public class ToonNotInGroupException extends ResistanceException {
    public ToonNotInGroupException(final Toon toon) {
        super("Toon %s is not in the group".formatted(toon.name()));
    }
}
