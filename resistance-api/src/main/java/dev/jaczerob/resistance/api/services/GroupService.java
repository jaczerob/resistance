package dev.jaczerob.resistance.api.services;

import dev.jaczerob.resistance.api.exceptions.*;
import dev.jaczerob.resistance.api.models.groups.Group;
import dev.jaczerob.resistance.api.models.groups.GroupFilter;
import dev.jaczerob.resistance.api.models.groups.GroupType;
import dev.jaczerob.resistance.api.models.toons.Toon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GroupService {
    private final Set<Group> groups = ConcurrentHashMap.newKeySet();
    private final Set<Toon> toonsInGroups = ConcurrentHashMap.newKeySet();

    public Group addToGroup(final Group group, final Toon toon) throws ResistanceException {
        if (this.toonsInGroups.contains(toon) || groupContainsToon(group, toon)) {
            throw new ToonAlreadyInGroupException(toon);
        } else if (!group.canAdd(toon)) {
            throw new ToonFailedCheckException(toon);
        } else {
            group.toons().add(toon);
            return group;
        }
    }

    public Group removeFromGroup(final Group group, final Toon toon) throws ResistanceException {
        if (!this.toonsInGroups.contains(toon) || !groupContainsToon(group, toon)) {
            throw new ToonNotInGroupException(toon);
        } else if (group.leader().equals(toon)) {
            throw new ToonIsLeaderException(toon);
        } else {
            group.toons().remove(toon);
            return group;
        }
    }

    public void deleteGroup(final Group group) throws ResistanceException {
        if (!this.groups.remove(group)) {
            throw new GroupNotExistsException(group);
        }

        this.toonsInGroups.remove(group.leader());
        group.toons().forEach(this.toonsInGroups::remove);
    }

    public Group createGroup(final Toon toon, final GroupType groupType, final int maxSize, final String location, final String district, final GroupFilter... groupFilters) throws ResistanceException {
        if (this.toonsInGroups.contains(toon)) {
            throw new ToonAlreadyInGroupException(toon);
        }

        this.toonsInGroups.add(toon);

        final Group group = new Group(UUID.randomUUID(), toon, List.of(), groupFilters, groupType, maxSize, location, district);
        this.groups.add(group);

        return group;
    }

    public Optional<Group> getGroup(final Toon toon) {
        if (!this.toonsInGroups.contains(toon))
            return Optional.empty();

        return this.groups.stream().filter(group -> groupContainsToon(group, toon)).findFirst();
    }

    public Optional<Group> getGroup(final UUID id) {
        return this.groups.stream().filter(group -> group.id().equals(id)).findFirst();
    }

    public Set<Group> getGroups() {
        return Set.copyOf(this.groups);
    }

    private static boolean groupContainsToon(final Group group, final Toon toon) {
        return group.leader().equals(toon) || group.toons().contains(toon);
    }
}
