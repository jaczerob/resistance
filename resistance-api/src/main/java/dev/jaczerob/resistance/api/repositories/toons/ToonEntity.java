package dev.jaczerob.resistance.api.repositories.toons;

import dev.jaczerob.resistance.api.models.gags.*;
import dev.jaczerob.resistance.api.models.toons.Species;
import dev.jaczerob.resistance.api.models.toons.Toon;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class ToonEntity {
    @Id
    private UUID id;
    private String name;
    private int laff;
    private Species species;

    private int toonUpLevel;
    private boolean toonUpOrganic;

    private int trapLevel;
    private boolean trapOrganic;

    private int lureLevel;
    private boolean lureOrganic;

    private int soundLevel;
    private boolean soundOrganic;

    private int throwLevel;
    private boolean throwOrganic;

    private int squirtLevel;
    private boolean squirtOrganic;

    private int dropLevel;
    private boolean dropOrganic;

    public ToonEntity() {
    }

    public Toon toToon() {
        return new Toon(
                this.id,
                this.name,
                this.laff,
                this.species,
                new ToonUpGag(this.toonUpLevel, this.toonUpOrganic),
                new TrapGag(this.trapLevel, this.trapOrganic),
                new LureGag(this.lureLevel, this.lureOrganic),
                new SoundGag(this.soundLevel, this.soundOrganic),
                new ThrowGag(this.throwLevel, this.throwOrganic),
                new SquirtGag(this.squirtLevel, this.squirtOrganic),
                new DropGag(this.dropLevel, this.dropOrganic)
        );
    }

    public static ToonEntity fromToon(final Toon toon) {
        final ToonEntity toonEntity = new ToonEntity();
        toonEntity.setId(toon.id());
        toonEntity.setLaff(toon.laff());
        toonEntity.setName(toon.name());
        toonEntity.setSpecies(toon.species());

        toonEntity.setToonUpLevel(toon.toonUpGag().getLevel());
        toonEntity.setToonUpOrganic(toon.toonUpGag().getIsOrganic());

        toonEntity.setLureLevel(toon.lureGag().getLevel());
        toonEntity.setLureOrganic(toon.lureGag().getIsOrganic());

        toonEntity.setTrapLevel(toon.trapGag().getLevel());
        toonEntity.setTrapOrganic(toon.trapGag().getIsOrganic());

        toonEntity.setSoundLevel(toon.soundGag().getLevel());
        toonEntity.setSoundOrganic(toon.soundGag().getIsOrganic());

        toonEntity.setThrowLevel(toon.throwGag().getLevel());
        toonEntity.setThrowOrganic(toon.throwGag().getIsOrganic());

        toonEntity.setSquirtLevel(toon.squirtGag().getLevel());
        toonEntity.setSquirtOrganic(toon.squirtGag().getIsOrganic());

        toonEntity.setDropLevel(toon.dropGag().getLevel());
        toonEntity.setDropOrganic(toon.dropGag().getIsOrganic());

        return toonEntity;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLaff() {
        return this.laff;
    }

    public void setLaff(int laff) {
        this.laff = laff;
    }

    public Species getSpecies() {
        return this.species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public int getToonUpLevel() {
        return this.toonUpLevel;
    }

    public void setToonUpLevel(int toonUpLevel) {
        this.toonUpLevel = toonUpLevel;
    }

    public boolean isToonUpOrganic() {
        return this.toonUpOrganic;
    }

    public void setToonUpOrganic(boolean toonUpOrganic) {
        this.toonUpOrganic = toonUpOrganic;
    }

    public int getTrapLevel() {
        return this.trapLevel;
    }

    public void setTrapLevel(int trapLevel) {
        this.trapLevel = trapLevel;
    }

    public boolean isTrapOrganic() {
        return this.trapOrganic;
    }

    public void setTrapOrganic(boolean trapOrganic) {
        this.trapOrganic = trapOrganic;
    }

    public int getLureLevel() {
        return this.lureLevel;
    }

    public void setLureLevel(int lureLevel) {
        this.lureLevel = lureLevel;
    }

    public boolean isLureOrganic() {
        return this.lureOrganic;
    }

    public void setLureOrganic(boolean lureOrganic) {
        this.lureOrganic = lureOrganic;
    }

    public int getSoundLevel() {
        return this.soundLevel;
    }

    public void setSoundLevel(int soundLevel) {
        this.soundLevel = soundLevel;
    }

    public boolean isSoundOrganic() {
        return this.soundOrganic;
    }

    public void setSoundOrganic(boolean soundOrganic) {
        this.soundOrganic = soundOrganic;
    }

    public int getThrowLevel() {
        return this.throwLevel;
    }

    public void setThrowLevel(int throwLevel) {
        this.throwLevel = throwLevel;
    }

    public boolean isThrowOrganic() {
        return this.throwOrganic;
    }

    public void setThrowOrganic(boolean throwOrganic) {
        this.throwOrganic = throwOrganic;
    }

    public int getSquirtLevel() {
        return this.squirtLevel;
    }

    public void setSquirtLevel(int squirtLevel) {
        this.squirtLevel = squirtLevel;
    }

    public boolean isSquirtOrganic() {
        return this.squirtOrganic;
    }

    public void setSquirtOrganic(boolean squirtOrganic) {
        this.squirtOrganic = squirtOrganic;
    }

    public int getDropLevel() {
        return this.dropLevel;
    }

    public void setDropLevel(int dropLevel) {
        this.dropLevel = dropLevel;
    }

    public boolean isDropOrganic() {
        return this.dropOrganic;
    }

    public void setDropOrganic(boolean dropOrganic) {
        this.dropOrganic = dropOrganic;
    }
}
