package ma.fstt.services;

import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;
import ma.fstt.entities.Client;

@Stateless
public class ClientService extends AbstractService  <Client> {
    public ClientService() {
        super(Client.class);
    }

    public Client Seconnecter(Client c) {
        try {
            TypedQuery<Client> query = em.createQuery(
                    "SELECT cli FROM Client cli WHERE cli.email = :email AND cli.motDePasse = :mdp",
                    Client.class
            );
            query.setParameter("email", c.getEmail());
            query.setParameter("mdp", c.getMotDePasse());

            // Si le client existe → on le retourne
            return query.getSingleResult();

        } catch (jakarta.persistence.NoResultException e) {
            // Si aucun client trouvé → on l’enregistre puis on le retourne
            save(c);
            return c;
        }
    }
}