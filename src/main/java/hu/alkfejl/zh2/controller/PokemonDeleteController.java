package hu.alkfejl.zh2.controller;

import hu.alkfejl.zh2.dao.PokemonDAOImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PokemonDeleteController")
public class PokemonDeleteController extends HttpServlet {
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
            dao.deletePokemon(id);
        }

        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
