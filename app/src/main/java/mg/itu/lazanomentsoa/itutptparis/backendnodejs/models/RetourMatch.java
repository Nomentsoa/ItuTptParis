package mg.itu.lazanomentsoa.itutptparis.backendnodejs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetourMatch {
    @SerializedName("match")
    @Expose
    Match match;

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
