package ma.fstt.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.entities.Produit;
import ma.fstt.services.ProduitService;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")

public class ProductController extends HttpServlet {

    @EJB
    private ProduitService ps;



    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, ServletException {
        List<Produit> produits = ps.findAll();
        req.setAttribute("products", produits);
        req.getRequestDispatcher("home.jsp").forward(req, res);
    }

}
