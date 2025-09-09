package david.samso.projecteuf3.classes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Producte implements Serializable {

    private int id;
    private String nom;
    private double preu;
    private String descripcio;
    private String dataCaduca;

    static final String carpeta = ".data";
    static final String fitxerPro = carpeta + "/producte.dat";
    static final Fitxers fitx = new Fitxers();

    public Producte() {
    }

    public Producte(String nom, double preu, String descripcio, String dataCaduca) {
        this.id = System.identityHashCode(this);
        this.nom = nom;
        this.preu = preu;
        this.descripcio = descripcio;
        this.dataCaduca = dataCaduca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public double getPreu() {
        return preu;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public String getDataCaduca() {
        return dataCaduca;
    }

    @Override
    public String toString() {
        return nom + ";" + preu + ";" + descripcio + ";" + dataCaduca;
    }

    public void guardaProducte() throws IOException {
        fitx.creaDirectoriIO(carpeta);
        fitx.escriuObjecteFitxer(this, fitxerPro, true);
    }

    public List<Producte> convertirProducte(List<Object> proObj) {
        List<Producte> lisPro = new ArrayList<>();

        for (Object obj : proObj) {
            if (obj instanceof Producte) {
                lisPro.add((Producte) obj);
            }
        }
        return lisPro;
    }


    public List<Producte> retornaProducte() throws IOException, InterruptedException, ClassNotFoundException {
        List<Producte> llistaProductes = new ArrayList<>();
        List<Object> producteObject = fitx.retornaFitxerObjecteEnLlista(fitxerPro);
        llistaProductes = convertirProducte(producteObject);

        return llistaProductes;
    }

    public boolean eliminaProducte(String nomProducte) throws IOException, InterruptedException, ClassNotFoundException {
        List<Producte> producteList = retornaProducte();
        boolean eliminat = false;

        Iterator<Producte> iterator = producteList.iterator();
        while (iterator.hasNext()) {
            Producte pr = iterator.next();
            if (pr.getNom().equalsIgnoreCase(nomProducte)) {
                iterator.remove();
                eliminat = true;
                break;
            }
        }

        if (eliminat) {
            Fitxers fitx = new Fitxers();
            fitx.escriuObjecteFitxer(producteList, Producte.fitxerPro, false);
        }

        return eliminat;
    }


    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
