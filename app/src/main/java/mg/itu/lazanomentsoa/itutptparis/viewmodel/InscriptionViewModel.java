package mg.itu.lazanomentsoa.itutptparis.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Utilisateur;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.service.NodeJsRepository;

public class InscriptionViewModel extends AndroidViewModel {
    private NodeJsRepository nodeJsRepository;

    public InscriptionViewModel(@NonNull @NotNull Application application) {
        super(application);
        intiInscriptionViewModel();
    }


    public void intiInscriptionViewModel(){
        nodeJsRepository = NodeJsRepository.getNodeJsRepository();
    }

    public MutableLiveData<Utilisateur> checkIfUserNameExists(String userName){
        return nodeJsRepository.checkIfUserNameExists(userName);
    }


    public MutableLiveData<Utilisateur> createUtilisateur(Utilisateur utilisateur){
        return nodeJsRepository.createUtilisateur(utilisateur);
    }
}
