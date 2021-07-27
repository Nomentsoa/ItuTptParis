package mg.itu.lazanomentsoa.itutptparis.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import mg.itu.lazanomentsoa.itutptparis.R;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LoginRequestBody;
import mg.itu.lazanomentsoa.itutptparis.utils.SessionManager;
import mg.itu.lazanomentsoa.itutptparis.viewmodel.LoginViewModel;

public class LoginActivity extends AbstractBaseActivity {
    private final String TAG = LoginActivity.class.getName();
    private LoginViewModel loginViewModel;
    private Button btnLogin;
    private TextInputEditText etLogin, etPassword;
    private CardView cvChamps, cvFond;
    private TextView tvErreur;
    private LifecycleOwner myOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cvChamps = (CardView)findViewById(R.id.cv_champ);
        cvFond = (CardView)findViewById(R.id.cv_fond);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        myOwner = this;

        // initialisation
        btnLogin = (Button)findViewById(R.id.btn_login);
        etLogin = (TextInputEditText)findViewById(R.id.et_login);
        etPassword = (TextInputEditText)findViewById(R.id.et_password);
        tvErreur = (TextView)findViewById(R.id.tv_erreur);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading(false);
                loginViewModel.getUtilisateurByLoginAndPassword(new LoginRequestBody(etLogin.getText().toString(),etPassword.getText().toString())).observe( myOwner, utilisateur -> {
                    Log.i(TAG, "valeur de retour => " + utilisateur);
                    if(utilisateur != null){
                        if(utilisateur.getStatusCode() == 401){
                            tvErreur.setVisibility(View.VISIBLE);
                            tvErreur.setText(utilisateur.getMessage());
                        }else{
                            SessionManager.getInstance(getBaseContext()).saveNomConnectedUser(utilisateur.getNom());
                            SessionManager.getInstance(getBaseContext()).savePrenomConnectedUser(utilisateur.getPrenom());
                            SessionManager.getInstance(getBaseContext()).saveEmailConnectedUser(utilisateur.getEmail());
                            SessionManager.getInstance(getBaseContext()).saveIdConnectedUser(utilisateur.get_id());
                            startActivity(new Intent(LoginActivity.this, ContainerActivity.class));
                        }
                    }else{
                        tvErreur.setVisibility(View.VISIBLE);
                        tvErreur.setText(getResources().getText(R.string.erreur_connexion));
                    }
                    dismissLoading();
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // check if il y a un utilisateur connecté
        checkConnectedUser();

        tvErreur.setVisibility(View.GONE);
        etPassword.getText().clear();
        etLogin.getText().clear();
        etLogin.requestFocus();
    }

    private void checkConnectedUser(){
        if(SessionManager.getInstance(this).getIdConnectedUser() != null){
            startActivity(new Intent(LoginActivity.this, ContainerActivity.class));
        }else
        {
            cvFond.setVisibility(View.VISIBLE);
            cvChamps.setVisibility(View.VISIBLE);
        }

    }
}