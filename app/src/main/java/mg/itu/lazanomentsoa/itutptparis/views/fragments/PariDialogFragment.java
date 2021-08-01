package mg.itu.lazanomentsoa.itutptparis.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import mg.itu.lazanomentsoa.itutptparis.R;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Pari;

public class PariDialogFragment<T> extends DialogFragment {
    private TextView tvEquipeGagnante, tvQuote;
    private ConstraintLayout clBtnAnnuler;
    private EditText etSomme;
    private Button btnValider;
    private Pari selectedPari;
    private int cote;

    public PariDialogFragment(Pari pari, int cote){
        selectedPari = pari;
        this.cote = cote;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pari_dialog, container, false);
        tvEquipeGagnante = view.findViewById(R.id.tv_valeur_equipe_gagnante);
        tvQuote = view.findViewById(R.id.tv_valeur_quote);
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


}
