package mg.itu.lazanomentsoa.itutptparis.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LocalisationAgence;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.service.NodeJsRepository;

public class LocalisationAgenceViewModel extends AndroidViewModel {
    private NodeJsRepository nodeJsRepository;
    public LocalisationAgenceViewModel(@NonNull @NotNull Application application) {
        super(application);
        initLocalisationAgenceViewModel();
    }

    public void initLocalisationAgenceViewModel(){
        nodeJsRepository = NodeJsRepository.getNodeJsRepository();
    }

    public MutableLiveData<List<LocalisationAgence>> getAllLocalisationAgence(){
        return nodeJsRepository.getAllLocalisationAgence();
    }
}
