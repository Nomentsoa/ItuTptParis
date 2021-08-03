package mg.itu.lazanomentsoa.itutptparis.backendnodejs.service;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.BaseRetour;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LocalisationAgence;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LoginRequestBody;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Match;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Pari;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.RetourMatch;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Utilisateur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NodeJsRepository {
    public static NodeJsRepository nodeJsRepository;
    private NodeJsEndPoints nodeJsEndPoints;

    public static NodeJsRepository getNodeJsRepository(){
        if(nodeJsRepository == null){
            nodeJsRepository = new NodeJsRepository();
        }

        return nodeJsRepository;
    }

    private NodeJsRepository(){
        nodeJsEndPoints = NodeJsService.createServiceNodeJs(NodeJsEndPoints.class);
    }


    /**
     * Get utilisateur login
     * @param loginRequestBody
     * @return MutableLiveData
     */
    public MutableLiveData<Utilisateur> getUtililsateurByLoginAndPassword(LoginRequestBody loginRequestBody){
        MutableLiveData<Utilisateur> utilisateurMutableLiveData = new MutableLiveData<>();
        nodeJsEndPoints.getUtilisateurByLoginAndPassword(loginRequestBody).enqueue(new Callback<Utilisateur>() {
            @Override
            public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                if(response.code() == 200){
                    utilisateurMutableLiveData.setValue(response.body());
                }else if (response.code() == 401){
                    Utilisateur utilisateur = new Utilisateur();
                    utilisateur.setStatusCode(401);
                    utilisateur.setMessage("Utilisateur ou mot de passe incorrect!");
                    utilisateurMutableLiveData.setValue(utilisateur);
                }else
                    utilisateurMutableLiveData.setValue(null);
            }

            @Override
            public void onFailure(Call<Utilisateur> call, Throwable t) {
                Log.i("NodeJsRepository"," message => "+t.getMessage());
                utilisateurMutableLiveData.setValue(null);
            }
        });

        return utilisateurMutableLiveData;
    }


    public MutableLiveData<List<Match>> getAllMatchAvenir(){
        MutableLiveData<List<Match>> allMatch= new MutableLiveData<>();
        nodeJsEndPoints.getAllMatchAVenir().enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                allMatch.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                allMatch.setValue(null);
            }
        });

        return allMatch;
    }


    // récuperation match par id
    public MutableLiveData<RetourMatch> getMatchById(String id){
        MutableLiveData<RetourMatch> matchMutableLiveData = new MutableLiveData<>();
        nodeJsEndPoints.getMatchById(id).enqueue(new Callback<RetourMatch>() {
            @Override
            public void onResponse(Call<RetourMatch> call, Response<RetourMatch> response) {
                matchMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RetourMatch> call, Throwable t) {
                matchMutableLiveData.setValue(null);
            }
        });

        return matchMutableLiveData;
    }

    // creation de pari
    public MutableLiveData<BaseRetour> createPari(Pari pari){
        MutableLiveData<BaseRetour> baseRetourMutableLiveData = new MutableLiveData<>();
        nodeJsEndPoints.createPari(pari).enqueue(new Callback<BaseRetour>() {
            @Override
            public void onResponse(Call<BaseRetour> call, Response<BaseRetour> response) {
                Log.i("pari"," pari response x=> "+ response.code());
                baseRetourMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BaseRetour> call, Throwable t) {
                Log.e("pari"," pari erreur =>  ");
                baseRetourMutableLiveData.setValue(null);
            }
        });
        return baseRetourMutableLiveData;
    }

    //get all localisation agence
    public MutableLiveData<List<LocalisationAgence>> getAllLocalisationAgence(){
        MutableLiveData<List<LocalisationAgence>> listMutableLiveData = new MutableLiveData<>();
        nodeJsEndPoints.getAllLocalisationAgence().enqueue(new Callback<List<LocalisationAgence>>() {
            @Override
            public void onResponse(Call<List<LocalisationAgence>> call, Response<List<LocalisationAgence>> response) {
                listMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<LocalisationAgence>> call, Throwable t) {
                listMutableLiveData.setValue(null);
            }
        });
        return listMutableLiveData;
    }

}
