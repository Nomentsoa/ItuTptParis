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
    private int montantDebit;

    @SerializedName("montantCredit")
    @Expose
    private int montantCredit;


    public MouvementJoueur(String date, String idUser, String idPari, int montantDebit) {
        this.date = date;
        this.idUser = idUser;
        this.idPari = idPari;
        this.montantDebit = montantDebit;
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

    public int getMontantDebit() {
        return montantDebit;
    }

    public void setMontantDebit(int montantDebit) {
        this.montantDebit = montantDebit;
    }

    public int getMontantCredit() {
        return montantCredit;
    }

    public void setMontantCredit(int montantCredit) {
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
