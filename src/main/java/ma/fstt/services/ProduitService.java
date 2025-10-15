package ma.fstt.services;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import ma.fstt.entities.Produit;

@Stateless
public class ProduitService extends AbstractService<Produit> {

    public ProduitService(){
        super(Produit.class);
    }



}
