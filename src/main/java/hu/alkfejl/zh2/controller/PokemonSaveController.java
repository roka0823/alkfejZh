package hu.alkfejl.zh2.controller;

import hu.alkfejl.zh2.dao.PokemonDAOImpl;
import hu.alkfejl.zh2.model.Pokemon;
import hu.alkfejl.zh2.model.PokemonType;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PokemonSaveController")
public class PokemonSaveController extends HttpServlet {

    private PokemonDAOImpl dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = PokemonDAOImpl.getInstance(getServletContext().getInitParameter("driver"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pokemon pokemon = new Pokemon();
        try {
            pokemon.setId(Integer.parseInt(req.getParameter("id")));
        } catch (NumberFormatException e) {
            // Ez azért maradhat üresen (kivételesen!), mert ha új pokemont viszünk fel, annak az id-ja: "" és ez nem
            //konvertálható számmá. Persze ennél lehet elegánsabban is!
        }

        pokemon.setPokemonName(req.getParameter("name"));
        pokemon.setType(PokemonType.findByName(req.getParameter("type")));
        pokemon.setGender(req.getParameter("gender"));
        pokemon.setCaught((req.getParameter("caught") != null));

        System.out.println(pokemon);

        dao.savePokemon(pokemon);
        resp.sendRedirect("index.jsp");
    }
}
