package mg.itu.lazanomentsoa.itutptparis.views.fragments.accueil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import mg.itu.lazanomentsoa.itutptparis.databinding.FragmentAccueilBinding;
import mg.itu.lazanomentsoa.itutptparis.viewmodel.AccueilViewModel;
import mg.itu.lazanomentsoa.itutptparis.views.AbstractBaseFragment;


public class AccueilFragment extends AbstractBaseFragment {
    private static final String TAG = AccueilFragment.class.getName();
    private AccueilViewModel accueilViewModel;
    private FragmentAccueilBinding binding;
    private LifecycleOwner lifecycleOwner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        accueilViewModel =  new ViewModelProvider(this).get(AccueilViewModel.class);
        lifecycleOwner = this;
        binding = FragmentAccueilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        showLoading(false);
        accueilViewModel.getAllMatchAvenir().observe(lifecycleOwner, matches -> {
            dismissLoading();
            Log.i(TAG, "match => " + matches);

        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}