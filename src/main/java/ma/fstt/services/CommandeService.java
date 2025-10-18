package ma.fstt.services;

import jakarta.ejb.Stateless;
import ma.fstt.entities.Commande;

import java.util.List;

@Stateless
public class CommandeService extends AbstractService<Commande>{

    public CommandeService (){
        super(Commande.class);
    }
    public List<Commande> findAllWithLignes(Long clientId) {
        return em.createQuery(
                "SELECT DISTINCT c FROM Commande c LEFT JOIN FETCH c.lignesCommande WHERE c.client.id = :id",
                Commande.class
        ).setParameter("id",clientId)
                .getResultList();
    }

    public List<Commande> findByClientId(Long clientId) {
        return em.createQuery(
                        "SELECT DISTINCT c FROM Commande c LEFT JOIN FETCH c.lignesCommande WHERE c.client.id = :id",
                        Commande.class
                )
                .setParameter("id", clientId)
                .getResultList();
    }


}
