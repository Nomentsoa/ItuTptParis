package mg.itu.lazanomentsoa.itutptparis.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Pari;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.service.NodeJsRepository;

public class PariViewModel extends AndroidViewModel {
    private NodeJsRepository nodeJsRepository;
    public PariViewModel(@NonNull @NotNull Application application) {
        super(application);
        initPariViewModel();
    }

    public void initPariViewModel(){
        nodeJsRepository = NodeJsRepository.getNodeJsRepository();
    }

    public MutableLiveData<List<Pari>> getAllParisByIdUser(String idUser){
        return nodeJsRepository.getAllParisByIdUser(idUser);
    }
}
