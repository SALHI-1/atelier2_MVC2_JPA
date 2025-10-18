package ma.fstt.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.entities.*;
import ma.fstt.services.CommandeService;
import ma.fstt.services.LignesCommandeService;
import ma.fstt.services.ProduitService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/panier")
public class PanierController extends HttpServlet {


    @EJB
    private ProduitService ps;

    @EJB
    private LignesCommandeService ls;

    @EJB

    private CommandeService cs;


    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = req.getParameter("action");


        switch (action){

            case "add":
                ajouterAuPanier(req);
                res.sendRedirect(req.getContextPath() + "/home");
                break;
            case "remove":
                supprimerDuPanier(req);
                res.sendRedirect(req.getContextPath() + "/panier");
                break;
            case "clean":
                viderPanier(req);
                res.sendRedirect(req.getContextPath()+"/panier");

            case "validate" :
                validerPanier(req);
                viderPanier(req);
                res.sendRedirect(req.getContextPath()+"/panier");

        }


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



    private void viderPanier(HttpServletRequest req){
        Panier panier = (Panier) req.getSession().getAttribute("panier");
        panier.viderPanier();
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
    private void validerPanier(HttpServletRequest req){

        Client client = (Client) req.getSession().getAttribute("client");
        if (client == null) {
            System.out.println("Aucun client connecté");
            return;
        }
        // ️Récupérer le panier de la session
        Panier panier = (Panier) req.getSession().getAttribute("panier");
        if (panier == null || panier.getProduits().isEmpty()) {
            System.out.println("Panier vide ou inexistant");
            return;
        }

        // Créer une nouvelle commande
        Commande commande = new Commande();
        commande.setClient(client);
        commande.setDateCommande(new Date());
        commande.setStatut("En cours");
        cs.save(commande);
        List<LignesCommande> lignes = new ArrayList<>();

        // 4️⃣ Parcourir les produits du panier et créer les lignes
        for (Map.Entry<Produit, Integer> entry : panier.getProduits().entrySet()) {
            Produit produit = entry.getKey();
            Integer quantite = entry.getValue();

            LignesCommande ligne = new LignesCommande();
            ligne.setProduit(produit);
            ligne.setQuantite(quantite);
            ligne.setCommande(commande);

            ls.save(ligne);

            lignes.add(ligne);


        }

        commande.setLignesCommande(lignes);





    }

}
