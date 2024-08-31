package dev.jaczerob.resistance.api.exceptions;

import dev.jaczerob.resistance.api.models.groups.Group;

public class GroupNotExistsException extends ResistanceException {
    public GroupNotExistsException(final Group group) {
        super("Group %s does not exist".formatted(group.id()));
    }
}
