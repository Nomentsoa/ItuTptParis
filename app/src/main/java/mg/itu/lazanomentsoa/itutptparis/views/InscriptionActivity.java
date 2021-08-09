package mg.itu.lazanomentsoa.itutptparis.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import mg.itu.lazanomentsoa.itutptparis.R;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Utilisateur;
import mg.itu.lazanomentsoa.itutptparis.backendspringboot.models.Role;
import mg.itu.lazanomentsoa.itutptparis.sqlite.model.Profil;
import mg.itu.lazanomentsoa.itutptparis.sqlite.viewmodel.ProfilSQLiteViewModel;
import mg.itu.lazanomentsoa.itutptparis.utils.SessionManager;
import mg.itu.lazanomentsoa.itutptparis.utils.StringConstant;
import mg.itu.lazanomentsoa.itutptparis.viewmodel.InscriptionViewModel;

public class InscriptionActivity extends AbstractBaseActivity {
    private final String TAG = InscriptionActivity.class.getName();
    private TextInputEditText etNom, etPrenom, etLogin, etEmail,etNumeroTelephone, etPassword, etPasswordRessaisie;
    private TextView tvErreur;
    private Button btnInscription;
    private InscriptionViewModel inscriptionViewModel;
    private LifecycleOwner lifecycleOwner;
    private ProfilSQLiteViewModel profilViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        initialisation();
        btnInscriptionListner();
    }

    private void initialisation(){
        inscriptionViewModel = new ViewModelProvider(this).get(InscriptionViewModel.class);
        profilViewModel = new ViewModelProvider(this).get(ProfilSQLiteViewModel.class);
        lifecycleOwner = this;

        etNom = findViewById(R.id.et_nom);
        etPrenom = findViewById(R.id.et_prenom);
        etLogin = findViewById(R.id.et_login);
        etEmail = findViewById(R.id.et_email);
        etNumeroTelephone = findViewById(R.id.et_numero_telephone);
        etPassword = findViewById(R.id.et_password);
        etPasswordRessaisie = findViewById(R.id.et_password_resaisie);

        tvErreur = findViewById(R.id.tv_erreur);

        btnInscription = findViewById(R.id.btn_inscription);
    }


    private void btnInscriptionListner(){
        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!StringConstant.isEmailValide(etEmail.getText().toString().trim())){
                    tvErreur.setVisibility(View.VISIBLE);
                    tvErreur.setText(getResources().getText(R.string.email_erreur));
                    return;
                }

                if(!etPassword.getText().toString().equals(etPasswordRessaisie.getText().toString())){
                    tvErreur.setVisibility(View.VISIBLE);
                    tvErreur.setText(getResources().getText(R.string.password_pas_identique));
                    return;
                }


                showLoading(false);
                inscriptionViewModel.checkIfUserNameExists(etLogin.getText().toString()).observe(lifecycleOwner, utilisateur -> {
                    if(utilisateur != null){
                        tvErreur.setVisibility(View.VISIBLE);
                        tvErreur.setText(getResources().getText(R.string.user_name_exists));
                    }else{
                        tvErreur.setVisibility(View.GONE);
                        Role role = new Role(1,"admin","1");

                        Utilisateur userToCreate = new Utilisateur(
                                role,
                                etLogin.getText().toString(),
                                etPassword.getText().toString(),
                                etNom.getText().toString(),
                                etPrenom.getText().toString(),
                                "active",
                                etEmail.getText().toString().trim(),
                                etNumeroTelephone.getText().toString(),
                                null);

                        inscriptionViewModel.createUtilisateur(userToCreate).observe(lifecycleOwner, utilisateurCreated -> {
                            insertInSQLiteAndChangeActivity(utilisateurCreated);
                            Toast.makeText(getBaseContext(), getResources().getText(R.string.user_created), Toast.LENGTH_SHORT).show();
                        });
                    }
                    dismissLoading();
                });


            }
        });
    }

    private void insertInSQLiteAndChangeActivity(Utilisateur utilisateur){
        Profil profilConnected = new Profil();
        profilConnected.setLogin(utilisateur.getLogin());
        profilConnected.setPassword(utilisateur.getPassword());
        profilConnected.setNom(utilisateur.getNom());
        profilConnected.setPrenom(utilisateur.getPrenom());
        profilConnected.setEtat(utilisateur.getEtat());
        profilConnected.setEmail(utilisateur.getEmail());
        profilConnected.setNumeroTelephone(utilisateur.getNumeroTelephone());
        profilConnected.setIdUser(utilisateur.get_id());

        profilViewModel.insert(profilConnected);

        SessionManager.getInstance(getBaseContext()).saveNomConnectedUser(utilisateur.getNom());
        SessionManager.getInstance(getBaseContext()).savePrenomConnectedUser(utilisateur.getPrenom());
        SessionManager.getInstance(getBaseContext()).saveEmailConnectedUser(utilisateur.getEmail());
        SessionManager.getInstance(getBaseContext()).saveIdConnectedUser(utilisateur.get_id());
        startActivity(new Intent(InscriptionActivity.this, ContainerActivity.class));
    }
}