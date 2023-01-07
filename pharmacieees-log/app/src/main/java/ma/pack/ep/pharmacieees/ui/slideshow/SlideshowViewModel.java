package ma.pack.ep.pharmacieees.ui.slideshow;
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

    public class SlideshowViewModel extends ViewModel {

        private final MutableLiveData<String> mText;

        public SlideshowViewModel() {
            mText = new MutableLiveData<>();
            handleSSLHandshake();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.11.105:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ma.pack.ep.pharmacieees.ui.slideshow.JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

            Call<List<Zone>> call = jsonPlaceHolderApi.getZones();
            call.enqueue(new Callback<List<Zone>>() {
                @Override
                public void onResponse(Call<List<Zone>> call, Response<List<Zone>> response) {
                    if (!response.isSuccessful()) {
                        mText.setValue("Code: " + response.code());
                        return;
                    }
                    List<Zone> zones = response.body(); String content = "";
                    for (Zone zone : zones) {
                        content += "ID: " + zone.getId() + "\n";
                        content += "Nom de zone: " + zone.getNom()+ "\n\n";
                        content += "Nom de ville: " + zone.getVille()+ "\n\n";
                    }mText.setValue(content);
                }

                @Override
                public void onFailure(Call<List<Zone>> call, Throwable t) {
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