package dev.jaczerob.resistance.api.models.groups;

import dev.jaczerob.resistance.api.models.toons.Toon;

import java.util.List;
import java.util.UUID;

public record Group(
        UUID id,
        Toon leader,
        List<Toon> toons,
        GroupFilter[] filters,
        GroupType groupType,
        int maxSize,
        String location,
        String district
) {
    public boolean canAdd(final Toon toon) {
        if (this.toons.size() + 1 >= this.maxSize)
            return false;

        for (final GroupFilter filter : this.filters)
            if (!filter.check(toon))
                return false;

        return true;
    }
}
