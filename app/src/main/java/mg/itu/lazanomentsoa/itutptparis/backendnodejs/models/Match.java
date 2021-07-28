package mg.itu.lazanomentsoa.itutptparis.backendnodejs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import mg.itu.lazanomentsoa.itutptparis.backendspringboot.models.Categorie;
import mg.itu.lazanomentsoa.itutptparis.backendspringboot.models.Equipe;

public class Match {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("heure")
    @Expose
    private String heure;

    @SerializedName("coteequipe1")
    @Expose
    private int coteEquipe1;

    @SerializedName("coteequipe2")
    @Expose
    private int coteEquip2;

    @SerializedName("etat")
    @Expose
    private String etat;

    @SerializedName("idcategorie")
    @Expose
    private Categorie categorie;

    @SerializedName("idequipe1")
    @Expose
    private Equipe equipe1;

    @SerializedName("idequipe2")
    @Expose
    private Equipe equipe2;

    @SerializedName("codeMacthNull")


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getCoteEquipe1() {
        return coteEquipe1;
    }

    public void setCoteEquipe1(int coteEquipe1) {
        this.coteEquipe1 = coteEquipe1;
    }

    public int getCoteEquip2() {
        return coteEquip2;
    }

    public void setCoteEquip2(int coteEquip2) {
        this.coteEquip2 = coteEquip2;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Equipe getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }

    public Equipe getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }
}
