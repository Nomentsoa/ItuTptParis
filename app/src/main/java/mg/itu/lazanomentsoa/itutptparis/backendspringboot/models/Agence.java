package mg.itu.lazanomentsoa.itutptparis.backendspringboot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Agence {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nom")
    @Expose
    private String nom;

    public Agence(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
