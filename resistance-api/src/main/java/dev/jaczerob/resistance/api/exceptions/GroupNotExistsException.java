package dev.jaczerob.resistance.api.exceptions;

import java.util.UUID;

public class GroupNotExistsException extends ResistanceException {
    public GroupNotExistsException(final UUID id) {
        super("Group %s does not exist".formatted(id));
    }
}
