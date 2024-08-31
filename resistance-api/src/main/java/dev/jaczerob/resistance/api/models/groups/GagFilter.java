package dev.jaczerob.resistance.api.models.groups;

import dev.jaczerob.resistance.api.models.gags.GagType;

public record GagFilter(
        GagType gagType,
        int level
) {
    @Override
    public String toString() {
        return "Min %s: %d".formatted(this.gagType, this.level);
    }
}
