package samso.duarte.pp_samso_duarte.classes;

import java.io.Serializable;

public class Usuari implements Serializable {

    private String nom;
    private String cognom;
    private String usuari;
    private String contrasenya;
    private boolean esAdministrador;

    // Constructor complet per crear un nou usuari
    public Usuari(String nom, String cognom, String usuari, String contrasenya, boolean esAdministrador) {
        this.nom = nom;
        this.cognom = cognom;
        this.usuari = usuari;
        this.contrasenya = contrasenya;
        this.esAdministrador = esAdministrador;
    }

    // Constructor per a validació d'usuari (nom d'usuari i contrasenya)
    public Usuari(String usuari, String contrasenya) {
        this.usuari = usuari;
        this.contrasenya = contrasenya;
    }

    // Getters i setters per als atributs
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("El nom no pot ser nul o buit");
        }
        this.nom = nom.trim();
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public boolean isAdministrador() {
        return esAdministrador;
    }

    public void setAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

    // Mètode per validar usuari (exemple de validació bàsica)
    public boolean validarUsuari(String usuari, String contrasenya) {
        return this.usuari.equals(usuari) && this.contrasenya.equals(contrasenya);
    }

    // Mètode per mostrar la informació de l'usuari en format String
    @Override
    public String toString() {
        return "Usuari{" +
                "nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                ", usuari='" + usuari + '\'' +
                ", esAdministrador=" + esAdministrador +
                '}';
    }
}
