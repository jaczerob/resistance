package dev.jaczerob.resistance.api.models.gags;

public class SoundGag extends Gag {
    public SoundGag(final int level, final boolean isOrganic) {
        super(GagType.SOUND, level, isOrganic);
    }
}