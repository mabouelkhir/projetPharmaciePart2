package ma.pack.ep.pharmacieees.ui.villes;

public class Ville {

    private int id;
    private String nom;

    public int getId() {

        return id;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return    nom ;
    }
}
