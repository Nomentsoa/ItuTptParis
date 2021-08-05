package mg.itu.lazanomentsoa.itutptparis.sqlite.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.sqlite.ProfilDao;
import mg.itu.lazanomentsoa.itutptparis.sqlite.TPTDatabase;
import mg.itu.lazanomentsoa.itutptparis.sqlite.model.Profil;

public class ProfilRepository {
    private ProfilDao profilDao;
    private LiveData<List<Profil>> profilListLiveData;
    public ProfilRepository(Application application){
        TPTDatabase database = TPTDatabase.getInstance(application);
        profilDao = database.profilDao();
        profilListLiveData = profilDao.getAllProfil();
    }

    public LiveData<Profil> getProfilByIdUser(String idUser){
        return profilDao.getProfil(idUser);
    }

    public LiveData<List<Profil>> getAllProfil(){
        return profilListLiveData;
    }

    public void insert(Profil profil){
        new InsertProfilAsyncTask(profilDao).execute(profil);
    }

    public void update(Profil profil){
        new UpdateProfilAsyncTask(profilDao).execute(profil);
    }

    public static class InsertProfilAsyncTask extends AsyncTask<Profil, Void, Void>{
        private ProfilDao profilDao;
        private InsertProfilAsyncTask(ProfilDao profilDao){
            this.profilDao = profilDao;
        }

        @Override
        protected Void doInBackground(Profil... profils) {
            profilDao.Insert(profils[0]);
            return null;
        }
    }

    private static class UpdateProfilAsyncTask extends AsyncTask<Profil, Void, Void>{
        private ProfilDao profilDao;
        private UpdateProfilAsyncTask(ProfilDao profilDao){

            this.profilDao = profilDao;
        }

        @Override
        protected Void doInBackground(Profil... profils) {
            profilDao.Update(profils[0]);
            return null;
        }
    }
}
