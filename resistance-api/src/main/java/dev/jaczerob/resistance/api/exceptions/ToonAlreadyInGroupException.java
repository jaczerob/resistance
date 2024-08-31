package dev.jaczerob.resistance.api.exceptions;

import dev.jaczerob.resistance.api.models.toons.Toon;

public class ToonAlreadyInGroupException extends ResistanceException {
    public ToonAlreadyInGroupException(final Toon toon) {
        super("%s is already in a group".formatted(toon.name()));
    }
}
