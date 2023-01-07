package ma.pack.ep.pharmacieees.ui.villes;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VillesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public VillesViewModel() {
        mText = new MutableLiveData<>();


        handleSSLHandshake();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.11.105:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ma.pack.ep.pharmacieees.ui.villes.JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(ma.pack.ep.pharmacieees.ui.villes.JsonPlaceHolderApi.class);

        Call<List<Ville>> call = jsonPlaceHolderApi.getVilles();
        call.enqueue(new Callback<List<Ville>>() {
            @Override
            public void onResponse(Call<List<Ville>> call, Response<List<Ville>> response) {
                if (!response.isSuccessful()) {
                    mText.setValue("Code: " + response.code());
                    return;
                }
                List<Ville> villes = response.body(); String content = "";
                for (Ville ville : villes) {
                    content += "ID: " + ville.getId() + "\n";
                    content += "Nom de ville: " + ville.getNom() + "\n\n";
                }mText.setValue(content);
            }

            @Override
            public void onFailure(Call<List<Ville>> call, Throwable t) {
               mText.setValue(t.getMessage());
            }
        });
    }
    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
    public LiveData<String> getText() {
        return mText;
    }
}