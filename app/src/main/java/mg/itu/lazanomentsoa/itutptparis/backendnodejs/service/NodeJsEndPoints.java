package mg.itu.lazanomentsoa.itutptparis.backendnodejs.service;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LoginRequestBody;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Utilisateur;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NodeJsEndPoints {
    //utilisateur
    @POST("utilisateur/login")
    Call<Utilisateur> getUtilisateurByLoginAndPassword(@Body LoginRequestBody loginRequestBody);
}
