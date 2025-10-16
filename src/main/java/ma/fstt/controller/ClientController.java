package ma.fstt.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.fstt.entities.Client;
import ma.fstt.entities.Produit;
import ma.fstt.services.ClientService;
import ma.fstt.services.ProduitService;


import java.io.IOException;
import java.util.List;

@WebServlet("/login")

public class ClientController extends HttpServlet {

    @EJB
    private ClientService cs;

    @EJB
    private ProduitService ps;

protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {


        Client client = new Client();
        client.setEmail(req.getParameter("email"));
        client.setMotDePasse(req.getParameter("mdp"));
/*
        cs.save(client);
*/

    client = cs.Seconnecter(client);

    System.out.println("the client is : " + client);
    if(client!=null){

        req.getSession().setAttribute("client", client);

        // Charger les produits depuis le service
        List<Produit> produits = ps.findAll(); // ou produitService.findAll()
        System.out.println("la liste des produits : " + produits);
        req.setAttribute("products", produits);

        req.getRequestDispatcher("home.jsp").forward(req, res); // forward, pas redirect
    }else{

        System.out.println("the else scope!!");
        req.setAttribute("error","server problem!");
        req.getRequestDispatcher("login.jsp").forward(req,res);
    }



}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, res);
    }

}
