package ma.fstt.controller;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.fstt.entities.Client;
import ma.fstt.services.ClientService;

import java.io.IOException;

@WebServlet("/client")
public class ClientController extends HttpServlet {

    @EJB
    private ClientService cs;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String action = req.getParameter("action");

        if (action == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        switch (action) {

            case "login": {
                Client client = new Client();
                client.setEmail(req.getParameter("email"));
                client.setMotDePasse(req.getParameter("mdp"));

                Client clientSession = cs.Seconnecter(client);

                if (clientSession != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("client", clientSession);
                    res.sendRedirect(req.getContextPath() + "/home");
                } else {
                    req.setAttribute("error", "Email ou mot de passe incorrect.");
                    req.getRequestDispatcher("login.jsp").forward(req, res);
                }
                break;
            }

            case "logout": {



                req.getSession().invalidate();
                res.sendRedirect(req.getContextPath() + "/login.jsp");
                break;
            }

            default:
                res.sendRedirect(req.getContextPath() + "/login.jsp");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, res);
    }
}
