package hu.alkfejl.zh2.model;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private int id;
    private String pokemonName;
    private PokemonType type;
    private String gender;
    private boolean caught;

    public Pokemon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean caught) {
        this.caught = caught;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", pokemonName='" + pokemonName + '\'' +
                ", type=" + type +
                ", gender='" + gender + '\'' +
                ", caught=" + caught +
                '}';
    }
}
