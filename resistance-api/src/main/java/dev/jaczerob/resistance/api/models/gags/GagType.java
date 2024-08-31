package dev.jaczerob.resistance.api.models.gags;

public enum GagType {
    TOON_UP("Toon-Up"),
    TRAP("Trap"),
    LURE("Lure"),
    SOUND("Sound"),
    THROW("Throw"),
    SQUIRT("Squirt"),
    DROP("Drop");

    private final String name;

    GagType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
