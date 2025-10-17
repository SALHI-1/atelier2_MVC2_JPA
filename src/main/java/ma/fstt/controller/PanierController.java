package ma.fstt.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.entities.Panier;
import ma.fstt.entities.Produit;
import ma.fstt.services.ProduitService;

import java.io.IOException;

@WebServlet("/panier")
public class PanierController extends HttpServlet {


    @EJB
    private ProduitService ps;

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");


        switch (action){

            case "add":
                ajouterAuPanier(req);
                break;
            case "remove":
                supprimerDuPanier(req);

                break;



        }


        res.sendRedirect(req.getContextPath() + "/panier");
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // Exemple : afficher le panier
        Panier panier = (Panier) req.getSession().getAttribute("panier");
        req.setAttribute("panier", panier);
        req.getRequestDispatcher("panier.jsp").forward(req, res);
    }

    private void ajouterAuPanier(HttpServletRequest req) {
        Long produitId = Long.parseLong(req.getParameter("productId"));
        Produit produit = ps.findById(produitId);
        Panier panier = (Panier) req.getSession().getAttribute("panier");
        if (panier == null) panier = new Panier();
        panier.ajouterProduit(produit, 1);
        req.getSession().setAttribute("panier", panier);
    }



/*    private void afficherPanier(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Panier panier = (Panier) req.getSession().getAttribute("panier");
        req.setAttribute("panier", panier);
        req.getRequestDispatcher("panier.jsp").forward(req, res);
    }
*/
    private void supprimerDuPanier(HttpServletRequest req) {
        Long produitId = Long.parseLong(req.getParameter("productId"));
        Panier panier = (Panier) req.getSession().getAttribute("panier");
        if (panier != null) panier.supprimerProduit(produitId);
    }

}
