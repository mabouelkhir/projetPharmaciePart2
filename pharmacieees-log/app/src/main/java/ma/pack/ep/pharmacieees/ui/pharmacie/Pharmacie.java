package ma.pack.ep.pharmacieees.ui.pharmacie;


import com.google.gson.annotations.SerializedName;

import ma.pack.ep.pharmacieees.ui.garde.Garde;
import ma.pack.ep.pharmacieees.ui.slideshow.Zone;

public class Pharmacie {
    @SerializedName("id")
    private Integer id;
    @SerializedName("nom")
    private String nom;
    @SerializedName("adresse")
    private String adresse;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;

    private Zone zone;
    private Garde garde;

    public Pharmacie(String nom, String adresse, double latitude, double longitude) {
        this.nom = nom;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Pharmacie(String adresse, double latitude, double longitude, String nom, Zone Zone) {
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nom = nom;
        this.zone=zone;
    }

    public Pharmacie(Integer id, String nom, String adresse, double latitude, double longitude, Zone zone, Garde garde) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.latitude = latitude;
        this.longitude = longitude;
        this.zone = zone;
        this.garde = garde;
    }

    public Garde getGarde() {
        return garde;
    }

    public void setGarde(Garde garde) {
        this.garde = garde;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}