package mg.itu.lazanomentsoa.itutptparis.backendspringboot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Role {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("rang")
    @Expose
    private String rang;

    public Role(int id, String nom, String rang) {
        this.id = id;
        this.nom = nom;
        this.rang = rang;
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

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", rang='" + rang + '\'' +
                '}';
    }
}
