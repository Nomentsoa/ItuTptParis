package mg.itu.lazanomentsoa.itutptparis.views.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

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
import mg.itu.lazanomentsoa.itutptparis.viewmodel.LocalisationAgenceViewModel;
import mg.itu.lazanomentsoa.itutptparis.views.AbstractBaseFragment;
import mg.itu.lazanomentsoa.itutptparis.views.adapter.LocalisationAgenceSpinnerAdapter;


public class MapFragment extends AbstractBaseFragment {
    private final String TAG = MapFragment.class.getName();
    private FragmentMapBinding binding;
    private LocalisationAgenceViewModel localisationAgenceViewModel;
    private LifecycleOwner lifecycleOwner;
    private LocalisationAgenceSpinnerAdapter localisationAgenceSpinnerAdapter;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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
}