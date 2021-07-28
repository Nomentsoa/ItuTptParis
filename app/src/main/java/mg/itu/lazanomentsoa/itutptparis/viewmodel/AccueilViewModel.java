package mg.itu.lazanomentsoa.itutptparis.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Match;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.service.NodeJsRepository;

public class AccueilViewModel extends AndroidViewModel {
    private NodeJsRepository nodeJsRepository;


    public AccueilViewModel(@NonNull @NotNull Application application) {
        super(application);
        initAccueilViewModel();
    }

    public void initAccueilViewModel(){
        nodeJsRepository = NodeJsRepository.getNodeJsRepository();
    }

    public MutableLiveData<List<Match>> getAllMatchAvenir(){
        return nodeJsRepository.getAllMatchAvenir();
    }
}
