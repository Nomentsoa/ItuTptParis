package mg.itu.lazanomentsoa.itutptparis.backendnodejs.service;

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
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NodeJsEndPoints {
    //authentification loginRequestbody(login, password)
    @POST("utilisateur/login")
    Call<Utilisateur> getUtilisateurByLoginAndPassword(@Body LoginRequestBody loginRequestBody);

    //get all match à venir
    @GET("match")
    Call<List<Match>> getAllMatchAVenir();

    @GET("match/{id}")
    Call<RetourMatch> getMatchById(@Path("id") String id);

    //create pari
    @POST("pari")
    Call<BaseRetour> createPari(@Body Pari pari);

    // localisation agence
    @GET("localisationAgence")
    Call<List<LocalisationAgence>> getAllLocalisationAgence();

    // getsolde utilsateur
    @GET("mvtJoueur/solde/{idUser}")
    Call<Solde> getSoldeConnectedUser(@Path("idUser") String idUser);

    // create mouvement utilisateur
    @POST("mvtJoueur")
    Call<BaseRetour> createMouvementJoueur(@Body MouvementJoueur mouvementJoueur);

}
