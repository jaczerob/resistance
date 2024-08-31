package dev.jaczerob.resistance.api.exceptions;

import dev.jaczerob.resistance.api.models.toons.Toon;

public class ToonFailedCheckException extends ResistanceException {
    public ToonFailedCheckException(final Toon toon) {
        super("%s is not a good fit for this group".formatted(toon.name()));
    }
}
