package ma.fstt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

/*
    @Column(unique = true, nullable = false)
*/
    private String email;

    private String motDePasse;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Commande> commandes;

    public Client() {
    }



    // --- Méthodes métier ---
/*
    public void seConnecter() {
        System.out.println(nom + " s'est connecté.");
    }
*/

    public Client(String nom, String email, String motDePasse, List<Commande> commandes) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.commandes = commandes;
    }
    public Client(Client c) {
        this.id = c.id;
        this.email = c.email;
        this.motDePasse = c.motDePasse;
    }


    public void consulterVitrine() {
        System.out.println(id + " consulte la vitrine.");
    }

    public void ajouterAuPanier(Produit p) {
        System.out.println(id + " ajoute " + p.getNom() + " au panier.");
    }


}
