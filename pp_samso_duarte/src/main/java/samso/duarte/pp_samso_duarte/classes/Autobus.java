package samso.duarte.pp_samso_duarte.classes;

import samso.duarte.pp_samso_duarte.metodes.Fitxers;

import java.io.IOException;
import java.util.Random;

public class Autobus implements Vehicle {

    // <editor-fold defaultstate="collapsed" desc="Propietats">

    private String matricula;
    private String model;
    private Double potencia;

    private int numPlaces;

    Fitxers f = new Fitxers();

    private static String rutaFitxer=dir+".autobussos.dat";
    // IMPORTANT.Noteu que la variable dir és una variable de la classe pare. PROTECTED

    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" constructors">
    public Autobus(){}

    public Autobus(String matricula, String model, Double potencia, int numPlaces) {
        this.matricula=matricula;
        this.model=model;
        this.potencia=potencia;
        this.numPlaces=numPlaces;
    }

    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters & Setters">


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


    public int getNumPlaces() {
        return numPlaces;
    }


    public static String getRutaFitxer() {
        return rutaFitxer;
    }





    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Mètodes">


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
    public <T> Object retornaVehiclesEnLlista(String arxiu,T classe) throws InterruptedException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IOException {

        return f.retornaFitxerObjecteEnLlista(arxiu,classe);

    }

    /**
     * Genera una matrícula aleatoria amb le utilitació
     * del mètode generarClau de la llibreria LlibreriaComuna
     * A aquesta matrícula se li afegirà aleatoriament
     * unes sigles d'un país europeu.
     *
     * Utilitzem polimorfisme, ja que aquest mètode està creat
     * en el pare, i reprogramat en aquesta classe derivada
     *
     * @return marícula amb sigles pais
     */
    public String generaMatricula(){
        int i;
        Random rnd=new Random();
        String mat="";
        char []lletra={'B','C','D','F','G','H','J','K','L','M','N','P',
                'Q','R','S','T','V','W','X','Y','Z'};

        for(i=0;i<4;i++){
            mat=mat+rnd.nextInt(10);        // número aleatori entre 0 i 9
        }

        for(i=0;i<3;i++){
            mat=mat+lletra[rnd.nextInt(lletra.length)];
        }


        String [] paissos={"ES","PT","FR","GB","DE","BE"};
        return mat=mat+" ("+paissos[rnd.nextInt(paissos.length)]+")";
    }

    /**
     * Ordena els autobussos per matrícula
     * @param o Autobús a ordenar
     * @return -1,0 o 1 en cas que sigui menor, igual o major
     */
    @Override
    public int compareTo(Object o) {

        Autobus au=(Autobus)o;
        if(this.matricula.compareTo(au.matricula)<0)
            return -1;
        if(this.matricula.compareTo(au.matricula)>0)
            return 1;

        return 0;
    }
    //</editor-fold>

}


