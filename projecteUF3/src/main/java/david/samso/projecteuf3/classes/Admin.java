package david.samso.projecteuf3.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Admin implements Serializable {
    private String nom;
    private String cognom;

    static final String carpeta = ".data";
    static final String fitxerAdmin = carpeta + "/administrador.dat";
    static final Fitxers fitx = new Fitxers();

    public Admin() {}

    public Admin(String nom, String cognom) {
        this.nom = nom;
        this.cognom = cognom;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }


    public void guardaAdmin() throws IOException {
        fitx.creaDirectoriIO(carpeta);
        fitx.escriuObjecteFitxer(this, fitxerAdmin, true);
    }

    public List<Admin> convertirAdmin(List<Object> proObj) {
        List<Admin> lisAda = new ArrayList<>();
        for (Object obj : proObj) {
            if (obj instanceof Admin) {
                lisAda.add((Admin) obj);
            }
        }
        return lisAda;
    }

    public List<Admin> retornaAdmin() throws IOException, InterruptedException, ClassNotFoundException {
        List<Object> adminObject = fitx.retornaFitxerObjecteEnLlista(fitxerAdmin);
        return convertirAdmin(adminObject);
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
