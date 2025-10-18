package ma.fstt.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateCommande;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<LignesCommande> lignesCommande;

    public Commande(Date dateCommande, String statut, Client client, List<LignesCommande> lignesCommande) {
        this.dateCommande = dateCommande;
        this.statut = statut;
        this.client = client;
        this.lignesCommande = lignesCommande;
    }

    public Commande() {
        this.dateCommande = new Date();
        this.statut = "En cours";
    }

    public void afficherDetails() {
        System.out.println("Commande #" + id + " (" + statut + ")");
    }

    public double getTotalePrix(){
        if(lignesCommande==null || lignesCommande.isEmpty())return 0;
        return lignesCommande.stream().mapToDouble(LignesCommande::getSousTotal).sum();

    }

    public List<LignesCommande> getLignesCommande() { return lignesCommande; }
    public void setLignesCommande(List<LignesCommande> lignesCommande) { this.lignesCommande = lignesCommande; }
}
