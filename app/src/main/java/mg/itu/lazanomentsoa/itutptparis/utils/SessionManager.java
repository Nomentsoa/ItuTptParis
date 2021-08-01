package mg.itu.lazanomentsoa.itutptparis.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;

import mg.itu.lazanomentsoa.itutptparis.views.ContainerActivity;

public class SessionManager {
    private static final String _PREF_KEY = "tpt_pari_pref";
    private static final String _PREF_NOM_CONNECTED_USER = "nom_connected_user";
    private static final String _PREF_PRENOM_CONNECTED_USER = "prenom_connected_user";
    private static final String _PREF_EMAIL_CONNECTED_USER = "email_connected_user";
    private static final String _PREF_ID_CONNECTED_USER = "id_connected_user";

    private static SessionManager instance;

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(_PREF_KEY, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    public static void ClearPrefSessionManager(Context context){
        editor.clear();
        editor.commit();
    }
    public static SessionManager getInstance(Context context){
        if(instance == null){
            instance = new SessionManager(context);
        }
        return instance;
    }

    public void saveNomConnectedUser(String nom){
        editor.putString(_PREF_NOM_CONNECTED_USER, nom);
        editor.commit();
    }

    public String getNomConnectedUser(){
        return sharedPreferences.getString(_PREF_NOM_CONNECTED_USER,null);
    }

    public void savePrenomConnectedUser(String prenom){
        editor.putString(_PREF_PRENOM_CONNECTED_USER, prenom);
        editor.commit();
    }

    public String getPrenomConnectedUser(){
        return sharedPreferences.getString(_PREF_PRENOM_CONNECTED_USER,null);
    }

    public void saveEmailConnectedUser(String email){
        editor.putString(_PREF_EMAIL_CONNECTED_USER, email);
        editor.commit();
    }

    public String getEmailConnectedUser(){
        return sharedPreferences.getString(_PREF_EMAIL_CONNECTED_USER,null);
    }

    public void saveIdConnectedUser(String id){
        editor.putString(_PREF_ID_CONNECTED_USER, id);
        editor.commit();
    }

    public String getIdConnectedUser(){
        return sharedPreferences.getString(_PREF_ID_CONNECTED_USER,null);
    }
}
