package mg.itu.lazanomentsoa.itutptparis.backendnodejs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import mg.itu.lazanomentsoa.itutptparis.backendspringboot.models.Equipe;

public class Pari {
    @SerializedName("idMatch")
    @Expose
    private String idMatch;

    @SerializedName("idEquipe")
    @Expose
    private Equipe equipe;

    @SerializedName("idUser")
    @Expose
    private String idUser;

    @SerializedName("mise")
    @Expose
    private Double mise;

    public Pari(String idMatch, Equipe equipe, String idUser, Double mise) {
        this.idMatch = idMatch;
        this.equipe = equipe;
        this.idUser = idUser;
        this.mise = mise;
    }

    public Pari(String idMatch, Equipe equipe, String idUser) {
        this.idMatch = idMatch;
        this.equipe = equipe;
        this.idUser = idUser;
    }

    public String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Double getMise() {
        return mise;
    }

    public void setMise(Double mise) {
        this.mise = mise;
    }

    @Override
    public String toString() {
        return "Pari{" +
                "idMatch='" + idMatch + '\'' +
                ", equipe=" + equipe +
                ", idUser='" + idUser + '\'' +
                ", mise=" + mise +
                '}';
    }
}
