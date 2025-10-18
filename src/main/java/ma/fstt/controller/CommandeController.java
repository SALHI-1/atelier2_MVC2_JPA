package ma.fstt.controller;

/*
import com.mysql.cj.xdevapi.Client;
*/
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
import ma.fstt.entities.Commande;
*/
import ma.fstt.entities.*;
import ma.fstt.services.CommandeService;
import ma.fstt.services.LignesCommandeService;

import java.io.IOException;
import java.util.List;

@WebServlet("/commandes")
public class CommandeController extends HttpServlet {

    @EJB
    CommandeService cs;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Récupérer le client connecté depuis la session
        Client client = (Client) req.getSession().getAttribute("client");

        if (client != null) {
            // Récupérer les commandes du client
            List<Commande> commandes = cs.findAllWithLignes(client.getId());

            // Stocker les commandes dans la requête (de préférence, pas la session)
            req.setAttribute("commandes", commandes);

            // Rediriger vers la page JSP qui affiche les commandes
            req.getRequestDispatcher("commandes.jsp").forward(req, resp);



        } else {
            resp.sendRedirect("login.jsp");
        }
    }
}
