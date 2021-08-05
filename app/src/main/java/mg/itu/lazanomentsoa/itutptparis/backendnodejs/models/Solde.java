package mg.itu.lazanomentsoa.itutptparis.backendnodejs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Solde {
    @SerializedName("solde")
    @Expose
    private int solde;

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
}
