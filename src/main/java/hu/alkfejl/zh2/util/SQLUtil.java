package hu.alkfejl.zh2.util;

public final class SQLUtil {

    public static final String SELECT_ALL_POKEMONS = "SELECT * FROM Pokemon;";
    public static final String SELECT_POKEMONS_BY_TYPE = "SELECT * FROM Pokemon WHERE type= ?;";
    public static final String ADD_POKEMON = "INSERT INTO Pokemon (pokemon_name, type, gender, caught) VALUES(?,?,?,?);";
    public static final String UPDATE_POKEMON = "UPDATE Pokemon SET pokemon_name=?, type=?, gender=?, caught=? WHERE id=?;";
    public static final String DELETE_POKEMON = "DELETE FROM Pokemon WHERE id=?;";
}
