package hu.alkfejl.zh2.dao;

import hu.alkfejl.zh2.model.Pokemon;
import hu.alkfejl.zh2.model.PokemonType;

import java.util.List;

public interface PokemonDAO {
    List<Pokemon> findAllPokemon();
    List<Pokemon> filterPokemon(PokemonType types);
    Pokemon savePokemon(Pokemon pokemon);
    void deletePokemon(int id);

    Pokemon findPokemonById(int id);
}
