package hu.alkfejl.zh2.dao;

import hu.alkfejl.zh2.model.Pokemon;
import hu.alkfejl.zh2.model.PokemonType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static hu.alkfejl.zh2.util.SQLUtil.*;

public class PokemonDAOImpl implements PokemonDAO {

    private static String CONN = "jdbc:sqlite:pokedex.db";
    private static PokemonDAOImpl instance;
    private PokemonDAOImpl(String connStr) {
        CONN = connStr;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            System.err.println("Hiba tortent: " + ex.getMessage());
            System.err.println("Tovabbi info:" + System.lineSeparator());
            ex.printStackTrace();
        }
    }

    public static PokemonDAOImpl getInstance(String connection){
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (PokemonDAOImpl.class) {
                if(instance==null) {
                    // if instance is null, initialize
                    instance = new PokemonDAOImpl(connection);
                }
            }
        }
        return instance;
    }

    @Override
    public List<Pokemon> findAllPokemon() {
        List<Pokemon>result =  new ArrayList<>();

        try(Connection c = DriverManager.getConnection(CONN);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_POKEMONS))
        {
            while(rs.next()){
                Pokemon pokemon = new Pokemon();
                pokemon.setId(rs.getInt("id"));
                pokemon.setPokemonName(rs.getString("pokemon_name"));
                pokemon.setType(PokemonType.findByName(rs.getString("type")));
                pokemon.setGender(rs.getString("gender"));
                pokemon.setCaught(rs.getInt("caught") == 1);

                result.add(pokemon);
            }

        }catch(SQLException ex){
            System.err.println("Hiba tortent: " + ex.getMessage());
            System.err.println("Tovabbi info:" + System.lineSeparator());
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Pokemon> filterPokemon(PokemonType types) {
        return findAllPokemon()
                .stream()
                .filter( s -> types.getName().equals(s.getType().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Pokemon savePokemon(Pokemon pokemon) {
        try(Connection c = DriverManager.getConnection(CONN);
            PreparedStatement pst = pokemon.getId() <= 0 ? c.prepareStatement(ADD_POKEMON,Statement.RETURN_GENERATED_KEYS) :
                                                         c.prepareStatement(UPDATE_POKEMON)
        ){

            pst.setString(1, pokemon.getPokemonName());
            pst.setString(2, pokemon.getType().getName());
            pst.setString(3, pokemon.getGender());
            pst.setInt(4, pokemon.isCaught() ? 1 : 0 );

            int id = pokemon.getId();
            if(0 < id){
                pst.setInt(5,id);
            }

            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                return null;
            }
            if(pokemon.getId() <= 0){
                ResultSet genKeys = pst.getGeneratedKeys();
                if(genKeys.next()){
                    pokemon.setId(genKeys.getInt(1));
                }
            }

        }catch(SQLException ex){
            System.err.println("Hiba tortent: " + ex.getMessage());
            System.err.println("Tovabbi info:" + System.lineSeparator());
            ex.printStackTrace();
        }
        return pokemon;
    }

    @Override
    public void deletePokemon(int id) {
        try(Connection c = DriverManager.getConnection(CONN);
            PreparedStatement pst = c.prepareStatement(DELETE_POKEMON);
        ) {
            pst.setInt(1,id);
            pst.executeUpdate();

        }catch (SQLException ex){
            System.err.println("Hiba tortent: " + ex.getMessage());
            System.err.println("Tovabbi info:" + System.lineSeparator());
            ex.printStackTrace();
        }
    }

    @Override
    public Pokemon findPokemonById(int id) {
        return findAllPokemon().stream().filter(poke -> poke.getId() == id).findFirst().get();
    }
}
