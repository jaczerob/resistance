package dev.jaczerob.resistance.api.exceptions;

import java.util.UUID;

public class ToonNotExistsException extends ResistanceException {
    public ToonNotExistsException(final UUID toonId) {
        super("%s does not exist".formatted(toonId));
    }
}
