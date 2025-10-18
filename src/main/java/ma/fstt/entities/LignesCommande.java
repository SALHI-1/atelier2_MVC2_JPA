package ma.fstt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class LignesCommande {
    public LignesCommande(int quantite, Produit produit, Commande commande) {
        this.id = id;
        this.quantite = quantite;
        this.produit = produit;
        this.commande = commande;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantite;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    public LignesCommande() {}

    public LignesCommande(Produit produit, int quantite) {
        this.produit = produit;
        this.quantite = quantite;
    }

    public double getSousTotal() {
        return produit.getPrix() * quantite;
    }



    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }

    public Commande getCommande() { return commande; }
    public void setCommande(Commande commande) { this.commande = commande; }
}
