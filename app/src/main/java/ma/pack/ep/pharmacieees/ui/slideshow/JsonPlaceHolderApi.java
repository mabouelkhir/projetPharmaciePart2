package ma.pack.ep.pharmacieees.ui.slideshow;




import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {


        @GET("/zones/all")
        Call<List<Zone>> getZones();
    }

