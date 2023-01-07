package ma.pack.ep.pharmacieees.ui.pharmacie;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchPharmacieResponse {
    @SerializedName("pharmacie")
    List<Pharmacie> pharmacieList;

    public FetchPharmacieResponse(List<Pharmacie> pharmacieList) {
        this.pharmacieList = pharmacieList;
    }

    public List<Pharmacie> getPharmacieList() {
        return pharmacieList;
    }

    public void setPharmacieList(List<Pharmacie> pharmacieList) {
        this.pharmacieList = pharmacieList;
    }

}
