package dev.jaczerob.resistance.api.models.gags;

public class ToonUpGag extends Gag {
    public ToonUpGag() { }

    public ToonUpGag(final int level, final boolean isOrganic) {
        super(GagType.TOON_UP, level, isOrganic);
    }
}