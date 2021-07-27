package mg.itu.lazanomentsoa.itutptparis.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LoginRequestBody;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Utilisateur;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.service.NodeJsRepository;

public class LoginViewModel extends AndroidViewModel {
    private NodeJsRepository nodeJsRepository;

    public LoginViewModel(@NonNull @NotNull Application application) {
        super(application);
        initLoginViewModel();
    }

    public void initLoginViewModel(){
        nodeJsRepository = NodeJsRepository.getNodeJsRepository();
    }

    public MutableLiveData<Utilisateur> getUtilisateurByLoginAndPassword(LoginRequestBody loginRequestBody){
        return nodeJsRepository.getUtililsateurByLoginAndPassword(loginRequestBody);
    }
}
