package ma.pack.ep.pharmacieees.ui.pharmacie;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import ma.pack.ep.pharmacieees.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PharmacieActivity extends AppCompatActivity {
    // creating variables for our edittext,
    // button, textview and progressbar.
    private EditText nome,adressee,latitudee,longitudee;
    private Button addpharmaciee;
    private TextView responseTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacie);

        // initializing our views
        nome = findViewById(R.id.nom);
       adressee = findViewById(R.id.adresse);
        latitudee = findViewById(R.id.latitude);
        longitudee = findViewById(R.id.longitude);
        addpharmaciee =findViewById(R.id.addPharmacie);
        responseTV = findViewById(R.id.idTVResponse);
        // adding on click listener to our button.
        addpharmaciee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (nome.getText().toString().isEmpty() && adressee.getText().toString().isEmpty()) {
                    Toast.makeText(PharmacieActivity.this, "SVP enter tout les valeurs", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                postData(nome.getText().toString(), adressee.getText().toString(), Double.parseDouble(String.valueOf(latitudee.getText())),  Double.parseDouble(String.valueOf(longitudee.getText())));
            }
        });
    }

    private void postData(String nom, String adresse, double latitude, double longitude) {
        handleSSLHandshake();
        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.11.105:8080")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        JsonPlaceHolderApi retrofitAPI = retrofit.create(JsonPlaceHolderApi.class);

        // passing data from our text fields to our modal class.
        Pharmacie modal = new Pharmacie(nom ,adresse,latitude,longitude);
        // calling a method to create a post and passing our modal class.
        Call<Pharmacie> call = retrofitAPI.createPharmacie(modal.getNom(),modal.getAdresse(),modal.getLongitude(),modal.getLatitude());
        // on below line we are executing our method.
        call.enqueue(new Callback<Pharmacie>() {
            @Override
            public void onResponse(Call<Pharmacie> call, Response<Pharmacie> response) {
                // this method is called when we get response from our api.
                Toast.makeText(PharmacieActivity.this, "Data added to API", Toast.LENGTH_SHORT).show();

                // on below line we are setting empty text
                // to our both edit text.
                nome.setText("");
                adressee.setText("");
                latitudee.setText("");
                longitudee.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                Pharmacie responseFromAPI = response.body();
                // on below line we are getting our data from modal class and adding it to our string.

                // below line we are setting our
                // string to our text view.

            }

            @Override
            public void onFailure(Call<Pharmacie> call, Throwable t) {
                Toast.makeText(PharmacieActivity.this,  t.getMessage(), Toast.LENGTH_SHORT).show();

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
}