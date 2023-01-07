package ma.pack.ep.pharmacieees.ui.pharmacie;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import ma.pack.ep.pharmacieees.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Map extends Fragment  implements OnMapReadyCallback {
    private GoogleMap mMap;
    String insertUrl = "http://192.168.11.105:8080";
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(insertUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
          }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, null, false);

        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.moveCamera(CameraUpdateFactory.zoomBy(5));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        final String TAG = "addPosition";
        mMap = googleMap;
        Call<List<Pharmacie>> call = jsonPlaceHolderApi.findAll();
        call.enqueue(new Callback<List<Pharmacie>>() {
            @Override
            public void onResponse(Call<List<Pharmacie>> call, Response<List<Pharmacie>> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG,response.code()+"map" );
                    return;
                }
                for(Pharmacie c:response.body()){
                    mMap.addMarker(new MarkerOptions().position(new
                                    LatLng(c.getLatitude(),
                                    c.getLongitude()))
                            .title(c.getNom() +" Adresse: "+c.getAdresse()));
                }
            }

            @Override
            public void onFailure(Call<List<Pharmacie>> call, Throwable t) {

            }
        });
    }
}