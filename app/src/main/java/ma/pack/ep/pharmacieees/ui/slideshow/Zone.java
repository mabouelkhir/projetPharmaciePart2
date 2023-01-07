package ma.pack.ep.pharmacieees.ui.slideshow;


import ma.pack.ep.pharmacieees.ui.villes.Ville;

public class Zone {
    private int id;
    private String nom;
    private Ville ville;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return  nom ;
    }
}
