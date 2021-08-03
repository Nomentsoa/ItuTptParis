package mg.itu.lazanomentsoa.itutptparis.views.fragments;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import mg.itu.lazanomentsoa.itutptparis.R;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LocalisationAgence;
import mg.itu.lazanomentsoa.itutptparis.databinding.FragmentMapBinding;
import mg.itu.lazanomentsoa.itutptparis.utils.GpsTraker;
import mg.itu.lazanomentsoa.itutptparis.viewmodel.LocalisationAgenceViewModel;
import mg.itu.lazanomentsoa.itutptparis.views.AbstractBaseFragment;
import mg.itu.lazanomentsoa.itutptparis.views.adapter.LocalisationAgenceSpinnerAdapter;


public class MapFragment extends AbstractBaseFragment {
    private final String TAG = MapFragment.class.getName();
    private FragmentMapBinding binding;
    private LocalisationAgenceViewModel localisationAgenceViewModel;
    private LifecycleOwner lifecycleOwner;
    private LocalisationAgenceSpinnerAdapter localisationAgenceSpinnerAdapter;

    private GpsTraker gpsTracker;

    private  GoogleMap mGoogleMap;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mGoogleMap = googleMap;
//            LatLng agence = getLocation();
//            googleMap.addMarker(new MarkerOptions().position(agence).title("Agence"));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(agence));
//            googleMap.setMinZoomPreference(16.0f);
//            googleMap.setMaxZoomPreference(20.0f);
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
    };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        checkPermission();



        lifecycleOwner = this;

        localisationAgenceViewModel = new ViewModelProvider(this).get(LocalisationAgenceViewModel.class);
        showLoading(false);
        localisationAgenceViewModel.getAllLocalisationAgence().observe(lifecycleOwner, localisationAgences -> {
            Log.i(TAG, "localisation => " + localisationAgences);

            localisationAgenceSpinnerAdapter = new LocalisationAgenceSpinnerAdapter(getActivity(), R.layout.agence_item_spinner,localisationAgences);
            binding.spAgence.setAdapter(localisationAgenceSpinnerAdapter);

            binding.spAgence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    LocalisationAgence localisationAgence = (LocalisationAgence)parent.getItemAtPosition(position);
                    Toast.makeText(getContext(), "agence => " +localisationAgence.getAgence().getNom(), Toast.LENGTH_LONG).show();

                    LatLng agence = new LatLng(Double.parseDouble(localisationAgence.getLatitude()), Double.parseDouble(localisationAgence.getLongitude()));
                    //LatLng agence = new LatLng(-20.286251, 45.985499);
                    //LatLng agence = getLocation();

                    mGoogleMap.addMarker(new MarkerOptions().position(agence).title("Agence"));
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(agence));
                    mGoogleMap.setMinZoomPreference(17.0f);
                    mGoogleMap.setMaxZoomPreference(20.0f);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



            dismissLoading();
        });


        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }



        return root;
    }

    private void checkPermission(){
        try {
            if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public LatLng getLocation(){
        gpsTracker = new GpsTraker(getContext());
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            Log.i(TAG, "log => "+longitude+" lat => "+latitude);
            return new LatLng(latitude, longitude);
        }else{
            gpsTracker.showSettingsAlert();
            return null;
        }
    }
}