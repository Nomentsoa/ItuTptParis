package mg.itu.lazanomentsoa.itutptparis.backendnodejs.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import mg.itu.lazanomentsoa.itutptparis.backendspringboot.models.Role;

public class Utilisateur extends BaseRetour{
    @SerializedName("idRole")
    @Expose
    private Role role;

    @SerializedName("_id")
    @Expose
    private String _id;


    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("prenom")
    @Expose
    private String prenom;

    @SerializedName("etat")
    @Expose
    private String etat;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("numeroTelephone")
    @Expose
    private String numeroTelephone;

    @SerializedName("imageProfil")
    @Expose
    private String image;

    public Utilisateur() {
    }

    public Utilisateur(Role role, String login, String password, String nom, String prenom, String etat, String email, String numeroTelephone, String image) {
        this.role = role;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.etat = etat;
        this.email = email;
        this.numeroTelephone = numeroTelephone;
        this.image = image;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "role=" + role +
                ", _id='" + _id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", etat='" + etat + '\'' +
                ", email='" + email + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
