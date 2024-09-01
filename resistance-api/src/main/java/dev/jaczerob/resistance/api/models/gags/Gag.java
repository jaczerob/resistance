package dev.jaczerob.resistance.api.models.gags;

public class Gag {
    private GagType gagType;
    private int level;
    private boolean isOrganic;

    public Gag() {
    }

    public Gag(final GagType gagType, final int level, final boolean isOrganic) {
        this.gagType = gagType;
        this.level = level;
        this.isOrganic = isOrganic;
    }

    public GagType getGagType() {
        return this.gagType;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean getIsOrganic() {
        return this.isOrganic;
    }

    public void setGagType(GagType gagType) {
        this.gagType = gagType;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setIsOrganic(boolean organic) {
        isOrganic = organic;
    }
}
