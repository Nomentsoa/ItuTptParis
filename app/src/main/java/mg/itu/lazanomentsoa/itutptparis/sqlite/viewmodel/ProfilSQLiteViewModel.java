package mg.itu.lazanomentsoa.itutptparis.sqlite.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.sqlite.model.Profil;
import mg.itu.lazanomentsoa.itutptparis.sqlite.repository.ProfilRepository;

public class ProfilSQLiteViewModel extends AndroidViewModel {
    private ProfilRepository profilRepository;
    public ProfilSQLiteViewModel(@NonNull @NotNull Application application) {
        super(application);
        profilRepository = new ProfilRepository(application);
    }

    public LiveData<List<Profil>> getAllProfil(){
        return profilRepository.getAllProfil();
    }

    public LiveData<Profil> getProfilByIdUser(String idUser){
        return profilRepository.getProfilByIdUser(idUser);
    }

    public void insert(Profil profil){
        profilRepository.insert(profil);
    }

    public void update(Profil profil){
        profilRepository.update(profil);
    }


}
