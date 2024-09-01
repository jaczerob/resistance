package dev.jaczerob.resistance.api.exceptions;

import java.util.UUID;

public class ToonExistsException extends ResistanceException {
    public ToonExistsException(final UUID toonId) {
        super("%s already exists".formatted(toonId));
    }
}
