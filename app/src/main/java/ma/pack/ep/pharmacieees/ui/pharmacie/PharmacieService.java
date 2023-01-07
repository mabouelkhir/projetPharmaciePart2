package ma.pack.ep.pharmacieees.ui.pharmacie;

import java.util.ArrayList;
import java.util.List;

public class PharmacieService implements IDao<Pharmacie> {
    private List<Pharmacie> pharmacies;
    private static PharmacieService instance;
    private PharmacieService() {
        this.pharmacies = new ArrayList<>();
    }
    public static PharmacieService getInstance() {
        if(instance == null)
            instance = new PharmacieService();
        return instance;
    }

    @Override
    public Pharmacie findById(int id) {
        for(Pharmacie s : pharmacies){
            if(s.getId() == id)
                return s;
        }
        return null;
    }

    @Override
    public List<Pharmacie> findAll() {
        return pharmacies;
    }
}
