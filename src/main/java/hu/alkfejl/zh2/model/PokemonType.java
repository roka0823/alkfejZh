package hu.alkfejl.zh2.model;

import java.util.Arrays;

public enum PokemonType {
    NORMAL("Normal"),
    GHOST("Ghost"),
    DRAGON("Dragon"),
    FAIRY("Fairy"),
    GRASS("Grass"),
    FIGHTING("Fighting"),
    WATER("Water"),
    POISON("Poison");

    private final String name;

    public String getName() {
        return name;
    }

    PokemonType(String name) {
        this.name = name;
    }

    public static PokemonType findByName(String synonym){
        return Arrays.stream(values())
                     .filter(value -> value.name.equals(synonym))
                     .findFirst()
                     .orElse(NORMAL);
    }
}
