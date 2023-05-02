package hu.alkfejl.zh2.controller;

import hu.alkfejl.zh2.dao.PokemonDAOImpl;
import hu.alkfejl.zh2.model.Pokemon;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PokemonUpdateController")
public class PokemonUpdateController extends HttpServlet {
    private PokemonDAOImpl dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = PokemonDAOImpl.getInstance(getServletContext().getInitParameter("driver"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Pokemon pokemon = dao.findPokemonById(id);
            req.setAttribute("pokemon", pokemon);
        }

        req.getRequestDispatcher("addpokemon.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
