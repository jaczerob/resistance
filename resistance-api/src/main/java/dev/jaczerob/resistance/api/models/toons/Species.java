package dev.jaczerob.resistance.api.models.toons;

public enum Species {
    BEAR("Bear"),
    CAT("Cat"),
    CROCODILE("Crocodile"),
    DEER("Deer"),
    DOG("Dog"),
    DUCK("Duck"),
    HORSE("Horse"),
    MONKEY("Monkey"),
    MOUSE("Mouse"),
    PIG("Pig"),
    RABBIT("Rabbit");

    private final String name;

    Species(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
