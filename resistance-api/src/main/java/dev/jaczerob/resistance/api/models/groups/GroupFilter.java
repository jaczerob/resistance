package dev.jaczerob.resistance.api.models.groups;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.jaczerob.resistance.api.models.toons.Toon;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupFilter {
    private final GroupFilterType groupFilterType;
    private final String description;
    @JsonIgnore
    private final Function<Toon, Boolean> filter;

    private GroupFilter(final GroupFilterType groupFilterType, final String description, final Function<Toon, Boolean> filter) {
        this.groupFilterType = groupFilterType;
        this.description = description;
        this.filter = filter;
    }

    @JsonGetter("groupFilterType")
    public String getGroupFilterType() {
        return this.groupFilterType.name();
    }

    public String getDescription() {
        return this.description;
    }

    public boolean check(final Toon toon) {
        return this.filter.apply(toon);
    }

    public static GroupFilter laff(final int laff) {
        return new GroupFilter(GroupFilterType.LAFF, "Min Laff: %d".formatted(laff), (t) -> t.laff() >= laff);
    }

    public static GroupFilter gags(final GagFilter... gagFilters) {
        return new GroupFilter(GroupFilterType.LAFF, Arrays.stream(gagFilters).map(GagFilter::toString).collect(Collectors.joining(", ")), (t) -> {
            for (final GagFilter gagFilter : gagFilters)
                if (t.getGagOfType(gagFilter.gagType()).getLevel() < gagFilter.level())
                    return false;

            return true;
        });
    }
}
