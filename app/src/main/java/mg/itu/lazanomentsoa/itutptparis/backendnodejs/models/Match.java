package mg.itu.lazanomentsoa.itutptparis.backendnodejs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import mg.itu.lazanomentsoa.itutptparis.backendspringboot.models.Categorie;
import mg.itu.lazanomentsoa.itutptparis.backendspringboot.models.Equipe;

public class Match {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("date")
    @Expose
    private Date date;

    @SerializedName("heure")
    @Expose
    private String heure;

    @SerializedName("coteequipe1")
    @Expose
    private float coteEquipe1;

    @SerializedName("coteequipe2")
    @Expose
    private float coteEquip2;

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

    @SerializedName("coteMatchNull")
    @Expose
    private float coteMatchNull;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public float getCoteEquipe1() {
        return coteEquipe1;
    }

    public void setCoteEquipe1(float coteEquipe1) {
        this.coteEquipe1 = coteEquipe1;
    }

    public float getCoteEquip2() {
        return coteEquip2;
    }

    public void setCoteEquip2(float coteEquip2) {
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

    public float getCoteMatchNull() {
        return coteMatchNull;
    }

    public void setCoteMatchNull(float coteMatchNull) {
        this.coteMatchNull = coteMatchNull;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", heure='" + heure + '\'' +
                ", coteEquipe1=" + coteEquipe1 +
                ", coteEquip2=" + coteEquip2 +
                ", etat='" + etat + '\'' +
                ", categorie=" + categorie +
                ", equipe1=" + equipe1 +
                ", equipe2=" + equipe2 +
                ", coteMatchNull=" + coteMatchNull +
                '}';
    }
}
