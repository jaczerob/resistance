package dev.jaczerob.resistance.api.models.gags;

import com.fasterxml.jackson.annotation.JsonGetter;

public abstract class Gag {
    private final GagType gagType;
    private final int level;
    private final boolean isOrganic;

    public Gag(final GagType gagType, final int level, final boolean isOrganic) {
        this.gagType = gagType;
        this.level = level;
        this.isOrganic = isOrganic;
    }

    @JsonGetter("gagType")
    public String getGagType() {
        return this.gagType.toString();
    }

    public int getLevel() {
        return this.level;
    }

    public boolean isOrganic() {
        return this.isOrganic;
    }
}
