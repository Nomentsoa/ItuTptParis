package mg.itu.lazanomentsoa.itutptparis.backendnodejs.service;

import java.util.List;

import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.BaseRetour;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LocalisationAgence;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.LoginRequestBody;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Match;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Pari;
import mg.itu.lazanomentsoa.itutptparis.backendnodejs.models.Utilisateur;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NodeJsEndPoints {
    //authentification loginRequestbody(login, password)
    @POST("utilisateur/login")
    Call<Utilisateur> getUtilisateurByLoginAndPassword(@Body LoginRequestBody loginRequestBody);

    //get all match à venir
    @GET("match")
    Call<List<Match>> getAllMatchAVenir();

    //create pari
    @POST("pari")
    Call<BaseRetour> createPari(@Body Pari pari);

    // localisation agence
    @GET("localisationAgence")
    Call<List<LocalisationAgence>> getAllLocalisationAgence();
}
