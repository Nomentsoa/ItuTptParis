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


        return root;
    }
}