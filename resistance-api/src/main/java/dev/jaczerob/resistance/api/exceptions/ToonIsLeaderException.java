package dev.jaczerob.resistance.api.exceptions;

import dev.jaczerob.resistance.api.models.toons.Toon;

public class ToonIsLeaderException extends ResistanceException {
    public ToonIsLeaderException(final Toon toon) {
        super("Toon %s is the leader".formatted(toon.name()));
    }
}
