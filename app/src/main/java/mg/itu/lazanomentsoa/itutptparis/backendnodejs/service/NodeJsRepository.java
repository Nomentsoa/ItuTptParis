package mg.itu.lazanomentsoa.itutptparis.backendnodejs.service;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LoginRequestBody;
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

}
