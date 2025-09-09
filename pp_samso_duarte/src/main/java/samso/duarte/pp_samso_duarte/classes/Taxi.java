package samso.duarte.pp_samso_duarte.classes;

import samso.duarte.pp_samso_duarte.metodes.Fitxers;

import java.io.IOException;
import java.util.Random;

public class Taxi implements Vehicle {

    // <editor-fold defaultstate="collapsed" desc="Propietats">
    // Variables de Taxi. Hem de declarar les que tenim a Vehicle (Interfície)
    // I les pròpies de Taxi (numerollicència)
    private String matricula;
    private String model;
    private Double potencia;
    private String numeroLlicencia;

    Fitxers f = new Fitxers();

    private static String rutaFitxer=dir+".taxis.dat";
    // IMPORTANT.Noteu que la variable dir és una variable de la interfície.
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // tindrem bàsicament 3 constructors. El per defecte, el del pare, el del pare+fill.

    // constructors:
    public Taxi(){}
    public Taxi(String matricula, String model, Double potencia, String numeroLlicencia) {
        this.matricula=matricula;
        this.model=model;
        this.potencia=potencia;
        this.numeroLlicencia=numeroLlicencia;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">
    // getters de la classe
    public String getNumeroLlicencia() {
        return numeroLlicencia;
    }

    public static String getRutaFitxer() {
        return rutaFitxer;
    }

    // getters obligats de la Interfície
    @Override
    public String getMatricula() {
        return matricula;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public Double getPotencia() {
        return potencia;
    }

    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Mètodes">

    /**
     * Mètode per guardar un objecte vehicle
     * @throws IOException Excepció d'E/S
     */
    public void guardaVehicleFitxer(String rutaFitxer) throws IOException {

        if (!f.existeix(dir))
            f.creaDirectori(dir);       // creem la carpeta contenidora si no existeix

        f.escriuObjecteFitxer(this,rutaFitxer,true);
    }

    /**
     * Mètode per retornar qualsevol objecte vehicle
     * ja retorna fins i tot formatat a la classe que pertany
     * @param arxiu ruta de l'arxiu
     * @param classe classe de l'objecte a transformar
     * @param <T> classe de l'objecte a transformar
     * @return llista classe de l'objecte a transformar
     * @throws InterruptedException excepció
     * @throws ClassNotFoundException excepció
     * @throws NoSuchFieldException excepció
     * @throws IllegalAccessException excepció
     * @throws IOException excepció
     */
    public <T> Object retornaVehiclesEnLlista(String arxiu, T classe)
            throws InterruptedException, ClassNotFoundException, NoSuchFieldException,
            IllegalAccessException, IOException {

        return f.retornaFitxerObjecteEnLlista(arxiu,classe);
    }

    /**
     *
     * POLIMORFISME. Ja que en la classe derivada taxi, utilitzarà aquest mètode
     * i en la classe derivada Autobús s'ha reescrit el mètode.
     *
     * Genera una matrícula aleatoria amb le utilitació
     * del mètode generarClau de la llibreria LlibreriaComuna
     *
     * Utilitzem polimorfisme, ja que aquest mètode està creat
     * en el pare, i reprogramat en una de les classes derivades
     *
     * @return marícula
     */
    public String generaMatricula(){
        int i;
        Random rnd=new Random();
        String mat="";
        char []lletra={'B','C','D','F','G','H','J','K','L','M','N','P','Q','R',
                'S','T','V','W','X','Y','Z'};
        for(i=0;i<4;i++){
            mat=mat+rnd.nextInt(10);        // número aleatori entre 0 i 9
        }
        for(i=0;i<3;i++){
            mat=mat+lletra[rnd.nextInt(lletra.length)];
        }
        return mat;
    }

    /**
     * Ordena els autobussos per matrícula
     * @param o Autobús a ordenar
     * @return -1,0 o 1 en cas que sigui menor, igual o major
     */
    @Override
    public int compareTo(Object o) {
        Taxi tx=(Taxi)o;
        if(this.potencia.compareTo(tx.potencia)<0)
            return -1;
        if(this.potencia.compareTo(tx.potencia)>0)
            return 1;

        return 0;
    }


    //</editor-fold>


}


