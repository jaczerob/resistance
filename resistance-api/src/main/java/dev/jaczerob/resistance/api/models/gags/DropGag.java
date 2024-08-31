package dev.jaczerob.resistance.api.models.gags;

public class DropGag extends Gag {
    public DropGag(final int level, final boolean isOrganic) {
        super(GagType.DROP, level, isOrganic);
    }
}
