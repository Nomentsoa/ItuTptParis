package mg.itu.lazanomentsoa.itutptparis.backendnodejs.service;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.BaseRetour;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LocalisationAgence;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LoginRequestBody;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Match;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.MouvementJoueur;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Pari;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.RetourMatch;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Solde;
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

    // get solde utilisateur by idUser
    public MutableLiveData<Solde> getSoldeByIdUserConnected(String idUser){
        MutableLiveData<Solde> soldeMutableLiveData = new MutableLiveData<>();
        nodeJsEndPoints.getSoldeConnectedUser(idUser).enqueue(new Callback<Solde>() {
            @Override
            public void onResponse(Call<Solde> call, Response<Solde> response) {
                soldeMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Solde> call, Throwable t) {
                soldeMutableLiveData.setValue(null);
            }
        });

        return soldeMutableLiveData;
    }

    // create mouvement jour
    public MutableLiveData<BaseRetour> createMouvementJoueur(MouvementJoueur mouvementJoueur){
        MutableLiveData<BaseRetour> baseRetourMutableLiveData = new MutableLiveData<>();
        nodeJsEndPoints.createMouvementJoueur(mouvementJoueur).enqueue(new Callback<BaseRetour>() {
            @Override
            public void onResponse(Call<BaseRetour> call, Response<BaseRetour> response) {
                baseRetourMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BaseRetour> call, Throwable t) {
                baseRetourMutableLiveData.setValue(null);
            }
        });
        return baseRetourMutableLiveData;
    }

    // récuperation des liste des paris by iduser
    public MutableLiveData<List<Pari>> getAllParisByIdUser(String idUser){
        MutableLiveData<List<Pari>> listMutableLiveData = new MutableLiveData<>();
        nodeJsEndPoints.getAllParisByIdUser(idUser).enqueue(new Callback<List<Pari>>() {
            @Override
            public void onResponse(Call<List<Pari>> call, Response<List<Pari>> response) {
                listMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Pari>> call, Throwable t) {
                listMutableLiveData.setValue(null);
            }
        });
        return listMutableLiveData;
    }

    // check if userName exist
    public MutableLiveData<Utilisateur> checkIfUserNameExists(String userName){
        MutableLiveData<Utilisateur> utilisateurMutableLiveData = new MutableLiveData<>();
        nodeJsEndPoints.checkIfUserNameExists(userName).enqueue(new Callback<Utilisateur>() {
            @Override
            public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                utilisateurMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Utilisateur> call, Throwable t) {
                utilisateurMutableLiveData.setValue(null);
            }
        });
        return utilisateurMutableLiveData;
    }

    // create utilisateur
    public MutableLiveData<Utilisateur> createUtilisateur(Utilisateur utilisateur){
        MutableLiveData<Utilisateur> utilisateurMutableLiveData = new MutableLiveData<>();
        nodeJsEndPoints.createUtilisateur(utilisateur).enqueue(new Callback<Utilisateur>() {
            @Override
            public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {
                Log.i("NodeJSRepository", "tafa => " + response.body());
                utilisateurMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Utilisateur> call, Throwable t) {
                utilisateurMutableLiveData.setValue(null);
                Log.i("NodeJSRepository", "erreur => " + t.getMessage());
            }
        });

        return  utilisateurMutableLiveData;
    }

}
