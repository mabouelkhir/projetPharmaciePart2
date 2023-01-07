package ma.pack.ep.pharmacieees.ui.pharmacie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import ma.pack.ep.pharmacieees.R;
import ma.pack.ep.pharmacieees.ui.slideshow.Zone;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class phamaFragment extends Fragment {
    RecyclerView recyclerView;
    List<Pharmacie> pharmacieList;
    PharmacieAdapter adapter = null;
    Spinner sp;
    List<Zone> ZoneList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        handleSSLHandshake();
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phama, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pharmacieList = new ArrayList<>();
        sp= view.findViewById(R.id.spinner);
        PharmacieService service = PharmacieService.getInstance();
        recyclerView = view.findViewById(R.id.recyclePharmacie);
        adapter = new PharmacieAdapter(service.findAll(), getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        handleSSLHandshake();

        //Retrofit
        Call<List<Pharmacie>> call = RetrofitClient.getInstance().getApi().findAll();
        call.enqueue(new Callback<List<Pharmacie>>() {
            @Override
            public void onResponse(Call<List<Pharmacie>> call, Response<List<Pharmacie>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    pharmacieList = response.body();
                    recyclerView.setAdapter(new PharmacieAdapter(pharmacieList, getActivity()));
                } else {
                    Toast.makeText(getActivity(), " response.body().getError()", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pharmacie>> call, Throwable t) {
                Toast.makeText(getActivity(), "houdaaa", Toast.LENGTH_LONG).show();
            }
        });

    Call<List<Zone>> calls = RetrofitClient.getInstance().getApi().Zone();
     calls.enqueue(new Callback<List<Zone>>() {
         @Override
         public void onResponse(Call<List<Zone>> call, Response<List<Zone>> response) {
             if (response.isSuccessful()) {
                 assert response.body() != null;
                 ZoneList = response.body();
                 ArrayAdapter<Zone> da=new ArrayAdapter<Zone>(getActivity(), androidx.transition.R.layout.support_simple_spinner_dropdown_item,ZoneList);
                 sp.setAdapter(da);
             } else {
                 Toast.makeText(getActivity(), " erreuuuur", Toast.LENGTH_LONG).show();
             }
         }

         @Override
         public void onFailure(Call<List<Zone>> call, Throwable t) {
             Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
         }
     });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //    Toast.makeText(getApplicationContext(), item+"", Toast.LENGTH_LONG).show();
                // Filter the list based on the selected item

            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null){
                    adapter.getFilter().filter(newText);
                    getPharmaciees();
                }
                return true;
            }
        });
    }

    private void getPharmaciees() {
        for (Pharmacie e : pharmacieList) {
            Log.d(e.getId() + "", e.getNom() + " " + e.getAdresse());
        }
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