package dev.jaczerob.resistance.api.models.toons;

import dev.jaczerob.resistance.api.models.gags.Gag;
import dev.jaczerob.resistance.api.models.gags.GagType;

import java.util.UUID;

public record Toon(
        UUID id,
        String name,
        int laff,
        Species species,

        Gag toonUpGag,
        Gag trapGag,
        Gag lureGag,
        Gag soundGag,
        Gag throwGag,
        Gag squirtGag,
        Gag dropGag
) {
    public Gag getGagOfType(final GagType gagType) {
        return switch (gagType) {
            case TOON_UP -> this.toonUpGag;
            case TRAP -> this.trapGag;
            case LURE -> this.lureGag;
            case SOUND -> this.soundGag;
            case THROW -> this.throwGag;
            case SQUIRT -> this.squirtGag;
            case DROP -> this.dropGag;
        };
    }
}
