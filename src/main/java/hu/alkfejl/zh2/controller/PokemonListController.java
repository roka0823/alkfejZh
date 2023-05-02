package hu.alkfejl.zh2.controller;

import hu.alkfejl.zh2.dao.PokemonDAOImpl;
import hu.alkfejl.zh2.model.PokemonType;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PokemonListController")
public class PokemonListController extends HttpServlet {

    private PokemonDAOImpl dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = PokemonDAOImpl.getInstance(getServletContext().getInitParameter("driver"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filter = req.getParameter("filter");
        if (filter != null && !filter.isEmpty()) {

            req.setAttribute("pokemons", dao.filterPokemon(PokemonType.findByName(filter)));
        } else {
            req.setAttribute("pokemons", dao.findAllPokemon());
        }

        resp.sendRedirect("index.jsp");
    }
}
