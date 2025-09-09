package david.samso.projecteuf3.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Usuari implements Serializable {
    private String nom;
    private String cognom;

    static final String carpeta = ".data";
    static final String fitxerusuari = carpeta + "/usuari.dat";
    static final Fitxers fitx = new Fitxers();

    public Usuari() {}

    public Usuari(String nom, String cognom) {
        this.nom = nom;
        this.cognom = cognom;

    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }


    public void guardaUsuari() throws IOException {
        fitx.creaDirectoriIO(carpeta);
        fitx.escriuObjecteFitxer(this, fitxerusuari, true);
    }

    public List<Usuari> convertirUsusari(List<Object> proObj) {
        List<Usuari> lisUsu = new ArrayList<>();
        for (Object obj : proObj) {
            if (obj instanceof Usuari) {
                lisUsu.add((Usuari) obj);
            }
        }
        return lisUsu;
    }

    public List<Usuari> retornaUsusari() throws IOException, InterruptedException, ClassNotFoundException {
        List<Object> usuariObject = fitx.retornaFitxerObjecteEnLlista(fitxerusuari);
        return convertirUsusari(usuariObject);
    }

    @Override
    public String toString() {
        return nom + ";" + cognom;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
