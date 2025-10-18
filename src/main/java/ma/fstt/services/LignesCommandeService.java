package ma.fstt.services;

import jakarta.ejb.Stateless;
import ma.fstt.entities.LignesCommande;

@Stateless

public class LignesCommandeService  extends AbstractService <LignesCommande> {


    public LignesCommandeService (){
        super(LignesCommande.class);
    }

}
