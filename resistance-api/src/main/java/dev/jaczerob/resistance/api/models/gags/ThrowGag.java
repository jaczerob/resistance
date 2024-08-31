package dev.jaczerob.resistance.api.models.gags;

public class ThrowGag extends Gag {
    public ThrowGag(final int level, final boolean isOrganic) {
        super(GagType.THROW, level, isOrganic);
    }
}
