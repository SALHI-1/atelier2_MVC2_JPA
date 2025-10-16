package ma.fstt.entities;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.management.ConstructorParameters;

@Entity
@Getter
@Setter

@ApplicationScoped
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private double prix;

    public Produit() {
    }

    private int stock;

    public Produit(String nom, String description, double prix, int stock) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.stock = stock;
    }


    public void afficherDetails() {
        System.out.println(nom + " - " + prix + " DH (" + stock + " en stock)");
    }


}
