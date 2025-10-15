package ma.fstt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String motDePasse;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commande> commandes;

    public Client() {
    }



    // --- Méthodes métier ---
    public void seConnecter() {
        System.out.println(nom + " s'est connecté.");
    }

    public Client(String nom, String email, String motDePasse, List<Commande> commandes) {
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.commandes = commandes;
    }

    public void consulterVitrine() {
        System.out.println(nom + " consulte la vitrine.");
    }

    public void ajouterAuPanier(Produit p) {
        System.out.println(nom + " ajoute " + p.getNom() + " au panier.");
    }


}
