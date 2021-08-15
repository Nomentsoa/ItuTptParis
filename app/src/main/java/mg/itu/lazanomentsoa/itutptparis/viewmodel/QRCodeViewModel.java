package mg.itu.lazanomentsoa.itutptparis.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Match;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.service.NodeJsRepository;

public class QRCodeViewModel extends AndroidViewModel {
    private NodeJsRepository nodeJsRepository;
    public QRCodeViewModel(@NonNull @NotNull Application application) {
        super(application);
        initQRCodeViewModel();
    }

    public void initQRCodeViewModel(){
        nodeJsRepository = NodeJsRepository.getNodeJsRepository();
    }

    public MutableLiveData<Match> getMatchById(String id){
        return nodeJsRepository.getMatchById(id);
    }
}
