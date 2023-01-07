package ma.pack.ep.pharmacieees.ui.pharmacie;

import java.util.List;

import ma.pack.ep.pharmacieees.ui.slideshow.Zone;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {
    @FormUrlEncoded
    @POST("/pharmacies/add")
    Call<Pharmacie> createPharmacie(
            @Field("nom") String nom,
            @Field("adresse") String adresse,
            @Field("longitude") double longitude,
            @Field("latitude") double latitude
    );//Body encapsule les donnees

    @GET("/pharmacies/all")
    Call<List<Pharmacie>> findAll();

    @GET("/zones/alls")
    Call<List<Zone>> Zone();


}
