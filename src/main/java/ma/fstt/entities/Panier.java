package ma.fstt.entities;

import java.util.*;

public class Panier {

    private String idTemp;

    public Panier(String idTemp, Date dateCreation, Map<Produit, Integer> produits) {
        this.idTemp = idTemp;
        this.dateCreation = dateCreation;
        this.produits = produits;
    }

    private Date dateCreation;
    private Map<Produit, Integer> produits;

    public Panier() {
        this.idTemp = UUID.randomUUID().toString();
        this.dateCreation = new Date();
        this.produits = new HashMap<>();
    }

    public void ajouterProduit(Produit produit, int qte) {
        produits.put(produit, produits.getOrDefault(produit, 0) + qte);
    }

    public void supprimerProduit(Produit produit) {
        produits.remove(produit);
    }

    public double calculerTotal() {
        return produits.entrySet()
                .stream()
                .mapToDouble(e -> e.getKey().getPrix() * e.getValue())
                .sum();
    }

    public void valider() {
        System.out.println("Panier validé, total : " + calculerTotal() + " DH");
    }

    // Getters
    public Map<Produit, Integer> getProduits() { return produits; }
}
