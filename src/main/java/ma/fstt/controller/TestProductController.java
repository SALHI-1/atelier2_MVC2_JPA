package ma.fstt.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.entities.Produit;
import ma.fstt.services.ProduitService;

import java.io.IOException;

@WebServlet("/test-produit")

public class TestProductController extends HttpServlet {

    @EJB
    private ProduitService ps;

    protected void doGet(HttpServletRequest req, HttpServletResponse res){


            Produit p = new Produit("clavier","nouveau",11,2);
            ps.save(p);
        try {
            res.getWriter().println("produit sauveagardé");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
