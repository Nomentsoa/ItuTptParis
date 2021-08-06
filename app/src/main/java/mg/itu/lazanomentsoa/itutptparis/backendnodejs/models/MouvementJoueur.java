package mg.itu.lazanomentsoa.itutptparis.backendnodejs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MouvementJoueur {
    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("idUser")
    @Expose
    private String idUser;

    @SerializedName("idPari")
    @Expose
    private String idPari;

    @SerializedName("montantDebit")
    @Expose
    private Double montantDebit;

    @SerializedName("montantCredit")
    @Expose
    private Double montantCredit;


    public MouvementJoueur(String date, String idUser, String idPari, Double montantDebit, Double montantCredit) {
        this.date = date;
        this.idUser = idUser;
        this.idPari = idPari;
        this.montantDebit = montantDebit;
        this.montantCredit = montantCredit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdPari() {
        return idPari;
    }

    public void setIdPari(String idPari) {
        this.idPari = idPari;
    }


    public Double getMontantDebit() {
        return montantDebit;
    }

    public void setMontantDebit(Double montantDebit) {
        this.montantDebit = montantDebit;
    }

    public Double getMontantCredit() {
        return montantCredit;
    }

    public void setMontantCredit(Double montantCredit) {
        this.montantCredit = montantCredit;
    }

    @Override
    public String toString() {
        return "MouvementJoueur{" +
                "date='" + date + '\'' +
                ", idUser='" + idUser + '\'' +
                ", idPari='" + idPari + '\'' +
                ", montantDebit=" + montantDebit +
                ", montantCredit=" + montantCredit +
                '}';
    }
}
