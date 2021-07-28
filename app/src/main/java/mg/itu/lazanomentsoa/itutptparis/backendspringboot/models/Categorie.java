package mg.itu.lazanomentsoa.itutptparis.backendspringboot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Categorie {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("description")
    @Expose
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
