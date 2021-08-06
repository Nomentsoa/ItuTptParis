package mg.itu.lazanomentsoa.itutptparis.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import mg.itu.lazanomentsoa.itutptparis.R;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.MouvementJoueur;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Pari;
import mg.itu.lazanomentsoa.itutptparis.utils.LoadingDialogFragment;
import mg.itu.lazanomentsoa.itutptparis.utils.SessionManager;
import mg.itu.lazanomentsoa.itutptparis.utils.StringConstant;
import mg.itu.lazanomentsoa.itutptparis.viewmodel.AccueilViewModel;

public class PariDialogFragment<T> extends DialogFragment {
    private final String TAG = PariDialogFragment.class.getName();
    private TextView tvEquipeGagnante, tvQuote, tvErreurSomme;
    private ConstraintLayout clBtnAnnuler;
    private EditText etSomme;
    private Button btnValider;
    private Pari selectedPari;
    private float cote;
    private AccueilViewModel accueilViewModel;
    private LifecycleOwner lifecycleOwner;

    protected LoadingDialogFragment loadingDialogFragment;
    protected FragmentManager fragmentManager;

    public PariDialogFragment(Pari pari, float cote){
        selectedPari = pari;
        this.cote = cote;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pari_dialog, container, false);

        accueilViewModel = new ViewModelProvider(this).get(AccueilViewModel.class);
        lifecycleOwner = this;

        tvEquipeGagnante = view.findViewById(R.id.tv_valeur_equipe_gagnante);
        tvQuote = view.findViewById(R.id.tv_valeur_quote);
        tvErreurSomme = view.findViewById(R.id.tv_erreur_somme);
        etSomme = view.findViewById(R.id.et_somme);
        clBtnAnnuler = view.findViewById(R.id.cl_btn_annuler);
        btnValider = view.findViewById(R.id.btn_valider);

        if(this.selectedPari.getEquipe() == null)
            tvEquipeGagnante.setText("Null");
        else
            tvEquipeGagnante.setText(this.selectedPari.getEquipe().getNom());

        tvQuote.setText(this.cote + "");

        clBtnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etSomme.getText().toString().length()==0 || Integer.parseInt(etSomme.getText().toString()) <=0){
                    tvErreurSomme.setVisibility(View.VISIBLE);
                    tvErreurSomme.setText(getResources().getString(R.string.mise_obligatoire));
                }else{
                    showLoading(false);
                    selectedPari.setMise(Double.parseDouble(etSomme.getText().toString()));
                    accueilViewModel.getSoldeByIdUserConnected(SessionManager.getInstance(getContext()).getIdConnectedUser()).observe(lifecycleOwner, solde -> {
                        if(solde != null){
                            Log.i(TAG, "solde => " +solde.getSolde());
                            Double sommeAMiser = Double.parseDouble(etSomme.getText().toString());
                            if(sommeAMiser > solde.getSolde()){
                                dismissLoading();
                                tvErreurSomme.setVisibility(View.VISIBLE);
                                tvErreurSomme.setText(getResources().getString(R.string.solde_insuffisant));
                            }else{
                                accueilViewModel.createPari(selectedPari).observe(lifecycleOwner, baseRetour -> {
                                    Log.i(TAG," pari response => " + baseRetour);
                                    if(baseRetour == null){
                                        dismissLoading();
                                        tvErreurSomme.setVisibility(View.VISIBLE);
                                        tvErreurSomme.setText(getResources().getString(R.string.erreur_mise_pari));
                                    }else{
                                        String dateMouvement = StringConstant.dateFormatToSend.format(Calendar.getInstance().getTime());
                                        MouvementJoueur mouvementJoueur = new MouvementJoueur(dateMouvement, SessionManager.getInstance(getContext()).getIdConnectedUser(), baseRetour.getMessage(), sommeAMiser, 0.0);
                                        accueilViewModel.createMouvementJoueur(mouvementJoueur).observe(lifecycleOwner, baseretourCreateMouvement ->{
                                            if(baseretourCreateMouvement != null){
                                                dismiss();
                                                dismissLoading();
                                                Toast.makeText(getContext(), getResources().getText(R.string.pari_cree), Toast.LENGTH_LONG).show();
                                            }else{
                                                dismiss();
                                                dismissLoading();
                                                Toast.makeText(getContext(), getResources().getText(R.string.error_mouvement_creation), Toast.LENGTH_LONG).show();
                                            }

                                        });

                                    }

                                });
                            }
                        }else{
                            dismissLoading();
                            tvErreurSomme.setVisibility(View.VISIBLE);
                            tvErreurSomme.setText(getResources().getString(R.string.erreur_mise_pari));
                        }

                    });

                }

            }
        });

        initListeners();
        return view;
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        setListener((T)context);
    }

    public void attachFragment(Fragment fragment){
        setListener((T)fragment);
    }

    private void setListener(T ctx){

    }

    private void initListeners() {

    }

    public void showLoading(boolean cancelable) {
        loadingDialogFragment = new LoadingDialogFragment();
        loadingDialogFragment.show(fragmentManager, StringConstant.createUniqueTag());
        loadingDialogFragment.setCancelable(cancelable);
    }

    public void dismissLoading() {
        loadingDialogFragment.dismiss();
    }


}
