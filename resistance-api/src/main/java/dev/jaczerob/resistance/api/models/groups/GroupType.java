package dev.jaczerob.resistance.api.models.groups;

public enum GroupType {
    BUILDING("Building"),
    BOSS("Boss"),
    FACILITY("Facility");

    private final String name;

    GroupType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
