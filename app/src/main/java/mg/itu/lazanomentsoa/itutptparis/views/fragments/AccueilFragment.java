package mg.itu.lazanomentsoa.itutptparis.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Match;
import mg.itu.lazanomentsoa.itutptparis.databinding.FragmentAccueilBinding;
import mg.itu.lazanomentsoa.itutptparis.viewmodel.AccueilViewModel;
import mg.itu.lazanomentsoa.itutptparis.views.AbstractBaseFragment;
import mg.itu.lazanomentsoa.itutptparis.views.adapter.MatchListAdapter;


public class AccueilFragment extends AbstractBaseFragment {
    private static final String TAG = AccueilFragment.class.getName();
    private AccueilViewModel accueilViewModel;
    private FragmentAccueilBinding binding;
    private LifecycleOwner lifecycleOwner;
    private MatchListAdapter matchListAdapter;
    private List<Match> matchList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        accueilViewModel =  new ViewModelProvider(this).get(AccueilViewModel.class);
        lifecycleOwner = this;
        binding = FragmentAccueilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        myContext = getActivity();

        showLoading(false);
        accueilViewModel.getAllMatchAvenir().observe(lifecycleOwner, matches -> {
            if(matches == null){

                binding.tvMatchNull.setVisibility(View.VISIBLE);
                binding.llAccueilMain.setVisibility(View.GONE);
            }else{

                matchList = matches;

                binding.tvMatchNull.setVisibility(View.GONE);
                binding.llAccueilMain.setVisibility(View.VISIBLE);

                matchListAdapter = new MatchListAdapter(matchList, myContext);
                binding.rvMatch.setHasFixedSize(true);
                binding.rvMatch.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.rvMatch.setAdapter(matchListAdapter);
            }
            dismissLoading();
        });

        binding.llBtnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txtRecherche = binding.etRecherche.getText().toString();
                if(txtRecherche.length() != 0){
                    List<Match> matchListRecherche = new ArrayList<>();
                    for (Match match : matchList ) {
                        if(match.getEquipe1().getNom().toLowerCase().contains(txtRecherche.toLowerCase()) || match.getEquipe2().getNom().toLowerCase().contains(txtRecherche.toLowerCase())){
                            matchListRecherche.add(match);
                        }
                    }
                    matchListAdapter.setNewMatchList(matchListRecherche);


                }else{
                    matchListAdapter.setNewMatchList(matchList);
                }
            }
        });


        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}