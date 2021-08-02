package mg.itu.lazanomentsoa.itutptparis.backendnodejs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import mg.itu.lazanomentsoa.itutptparis.backendspringboot.models.Agence;

public class LocalisationAgence {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("latitude")
    @Expose
    private String latitude;

    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("agence")
    @Expose
    private Agence agence;

    public LocalisationAgence(String id, String latitude, String longitude, Agence agence) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.agence = agence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    @Override
    public String toString() {
        return "LocalisationAgence{" +
                "id='" + id + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", agence=" + agence +
                '}';
    }
}
