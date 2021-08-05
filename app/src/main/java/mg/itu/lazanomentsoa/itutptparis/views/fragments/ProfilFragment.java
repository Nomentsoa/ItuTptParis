package mg.itu.lazanomentsoa.itutptparis.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import mg.itu.lazanomentsoa.itutptparis.databinding.FragmentProfilBinding;
import mg.itu.lazanomentsoa.itutptparis.sqlite.model.Profil;
import mg.itu.lazanomentsoa.itutptparis.sqlite.viewmodel.ProfilSQLiteViewModel;
import mg.itu.lazanomentsoa.itutptparis.utils.SessionManager;


public class ProfilFragment extends Fragment {
    private final String TAG = ProfilFragment.class.getName();
    private FragmentProfilBinding binding;
    private ProfilSQLiteViewModel profilSQLiteViewModel;
    private LifecycleOwner lifecycleOwner;
    private Profil profilToUpdate;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        profilSQLiteViewModel = new ViewModelProvider(this).get(ProfilSQLiteViewModel.class);
        lifecycleOwner = this;

//        profilSQLiteViewModel.getAllProfil().observe(lifecycleOwner, profils -> {
//            Log.i(TAG, "profils => "+ profils);
//        });



        binding = FragmentProfilBinding.inflate(inflater, container, false);
        profilSQLiteViewModel.getProfilByIdUser(SessionManager.getInstance(getContext()).getIdConnectedUser()).observe(lifecycleOwner, profil -> {
            binding.etNom.setText(profil.getNom());
            binding.etPrenom.setText(profil.getPrenom());
            binding.etEmail.setText(profil.getEmail());
            binding.etTelephone.setText(profil.getNumeroTelephone());

            profilToUpdate = profil;
        });


        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profilToUpdate.setNom(binding.etNom.getText().toString());
                profilToUpdate.setEmail(binding.etEmail.getText().toString());
                profilToUpdate.setPrenom(binding.etPrenom.getText().toString());
                profilToUpdate.setNumeroTelephone(binding.etTelephone.getText().toString());

                SessionManager.getInstance(getContext()).saveNomConnectedUser(profilToUpdate.getNom());
                SessionManager.getInstance(getContext()).savePrenomConnectedUser(profilToUpdate.getPrenom());
                SessionManager.getInstance(getContext()).saveEmailConnectedUser(profilToUpdate.getEmail());

                profilSQLiteViewModel.update(profilToUpdate);

                Toast.makeText(getContext(), "Profil local modifié", Toast.LENGTH_LONG).show();
                getActivity().finish();
                getActivity().startActivity(getActivity().getIntent());
            }
        });

        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}