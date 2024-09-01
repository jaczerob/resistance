package dev.jaczerob.resistance.api.models.groups;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.jaczerob.resistance.api.models.gags.GagType;
import dev.jaczerob.resistance.api.models.toons.Toon;

import java.util.function.BiFunction;

public class GroupFilter {
    private GroupFilterType groupFilterType;
    private String description;
    @JsonIgnore
    private BiFunction<Group, Toon, Boolean> filter;

    public GroupFilter() {
    }

    private GroupFilter(final GroupFilterType groupFilterType, final String description, final BiFunction<Group, Toon, Boolean> filter) {
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

    public void setGroupFilterType(GroupFilterType groupFilterType) {
        this.groupFilterType = groupFilterType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean check(final Group group, final Toon toon) {
        return this.filter.apply(group, toon);
    }

    public static GroupFilter laff(final int laff) {
        return new GroupFilter(GroupFilterType.LAFF, "Min Laff: %d".formatted(laff), (g, t) -> t.laff() >= laff);
    }

    public static GroupFilter gag(final GagType gagType, final int minLevel) {
        return new GroupFilter(GroupFilterType.LAFF, "Min %s: %d".formatted(gagType, minLevel), (g, t) -> t.getGagOfType(gagType).getLevel() >= minLevel);
    }

    public static GroupFilter size(final int maxSize) {
        return new GroupFilter(GroupFilterType.LAFF, "Max Size: %d".formatted(maxSize), (g, t) -> g.toons().size() + 1 < maxSize);
    }
}
