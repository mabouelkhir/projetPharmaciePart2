package ma.pack.ep.pharmacieees.ui.pharmacie;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL ="http://192.168.1.106:8080";
    private static RetrofitClient retrofitClient;
    private static Retrofit retrofit;
    private RetrofitClient(){
        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance(){
        if(retrofitClient==null){
            retrofitClient=new RetrofitClient();}
        return retrofitClient;
    }
    public JsonPlaceHolderApi getApi(){
        return retrofit.create(JsonPlaceHolderApi.class);
    }
}
