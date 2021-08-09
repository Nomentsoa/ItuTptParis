package mg.itu.lazanomentsoa.itutptparis.views.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;

import mg.itu.lazanomentsoa.itutptparis.databinding.FragmentParisListBinding;
import mg.itu.lazanomentsoa.itutptparis.utils.SessionManager;
import mg.itu.lazanomentsoa.itutptparis.viewmodel.PariViewModel;
import mg.itu.lazanomentsoa.itutptparis.views.AbstractBaseFragment;
import mg.itu.lazanomentsoa.itutptparis.views.adapter.ParisListAdapter;


public class ParisListFragment extends AbstractBaseFragment {
    private static final String TAG = ParisListFragment.class.getName();
    private PariViewModel pariViewModel;
    private FragmentParisListBinding binding;
    private LifecycleOwner lifecycleOwner;
    private ParisListAdapter parisListAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentParisListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        lifecycleOwner = this;
        pariViewModel = new ViewModelProvider((ViewModelStoreOwner) lifecycleOwner).get(PariViewModel.class);
        getListAllParis();

        return root;
    }

    private void getListAllParis(){
        showLoading(false);
        pariViewModel.getAllParisByIdUser(SessionManager.getInstance(getContext()).getIdConnectedUser()).observe(lifecycleOwner, paris -> {
            if(paris == null){
                binding.tvParisNull.setVisibility(View.VISIBLE);
                binding.llPariNonNull.setVisibility(View.GONE);
            }else{
                binding.tvParisNull.setVisibility(View.GONE);
                binding.llPariNonNull.setVisibility(View.VISIBLE);

                parisListAdapter = new ParisListAdapter(paris, lifecycleOwner);
                binding.rvParis.setHasFixedSize(true);
                binding.rvParis.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvParis.setAdapter(parisListAdapter);

            }

            Log.i(TAG, "list paris => " + paris);
            dismissLoading();
        });
    }
}