package ma.pack.ep.pharmacieees.ui.villes;




import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface JsonPlaceHolderApi {


        @GET("/villes/all")
        Call<List<Ville>> getVilles();
    }

