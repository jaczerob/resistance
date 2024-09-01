package dev.jaczerob.resistance.api.services;

import dev.jaczerob.resistance.api.exceptions.*;
import dev.jaczerob.resistance.api.models.groups.Group;
import dev.jaczerob.resistance.api.models.groups.GroupFilter;
import dev.jaczerob.resistance.api.models.groups.GroupType;
import dev.jaczerob.resistance.api.models.toons.Toon;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class GroupService {
    private final Map<UUID, Group> groups = new ConcurrentHashMap<>();
    private final Map<UUID, UUID> toonsInGroups = new ConcurrentHashMap<>();

    public void addToGroup(final Group group, final Toon toon) throws ResistanceException {
        if (this.toonsInGroups.containsKey(toon.id())) {
            throw new ToonAlreadyInGroupException(toon);
        } else if (!group.canAdd(toon)) {
            throw new ToonFailedCheckException(toon);
        } else {
            group.toons().add(toon);
        }
    }

    public void removeFromGroup(final Group group, final Toon toon) throws ResistanceException {
        if (!groupContainsToon(group, toon)) {
            throw new ToonNotInGroupException(toon);
        } else if (group.leader().equals(toon)) {
            throw new ToonIsLeaderException(toon);
        } else {
            group.toons().remove(toon);
        }
    }

    public void deleteGroup(final UUID groupId) throws ResistanceException {
        final Group group = this.groups.remove(groupId);
        if (group == null) {
            throw new GroupNotExistsException(groupId);
        }

        this.toonsInGroups.remove(group.leader().id());
        group.toons().stream().map(Toon::id).forEach(this.toonsInGroups::remove);
    }

    public Group createGroup(final Toon toon, final GroupType groupType, final String location, final String district, final GroupFilter[] groupFilters) throws ResistanceException {
        if (this.toonsInGroups.containsKey(toon.id())) {
            throw new ToonAlreadyInGroupException(toon);
        }

        final Group group = new Group(UUID.randomUUID(), toon, new CopyOnWriteArrayList<>(), groupFilters, groupType, location, district);
        this.toonsInGroups.put(toon.id(), group.id());
        this.groups.put(group.id(), group);

        return group;
    }

    public Optional<Group> getGroup(final Toon toon) {
        final UUID groupId = this.toonsInGroups.get(toon.id());
        if (groupId == null)
            return Optional.empty();

        return Optional.ofNullable(this.groups.get(groupId));
    }

    public Optional<Group> getGroup(final UUID id) {
        return Optional.ofNullable(this.groups.get(id));
    }

    public Set<Group> getGroups() {
        return Set.copyOf(this.groups.values());
    }

    private static boolean groupContainsToon(final Group group, final Toon toon) {
        return group.leader().equals(toon) || group.toons().contains(toon);
    }
}
