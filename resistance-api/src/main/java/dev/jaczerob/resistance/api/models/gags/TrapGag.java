package dev.jaczerob.resistance.api.models.gags;

public class TrapGag extends Gag {
    public TrapGag(final int level, final boolean isOrganic) {
        super(GagType.TRAP, level, isOrganic);
    }
}
