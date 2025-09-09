package samso.duarte.pp_samso_duarte.classes;

import samso.duarte.pp_samso_duarte.metodes.Fitxers;

import java.io.IOException;
import java.io.Serializable;

/**
 * Tot el que poseu en aquesta classe (Interfície)
 * Serà creat obligatòriament a les classes que
 * implementen aquesta interfície
 */
public interface Vehicle extends Serializable, Comparable{


    // <editor-fold defaultstate="collapsed" desc="Propietats i variables">


    // Podem declarar variables amb valor que estaran disponible al llarg
    // de les classes que implementen la interfície
    Fitxers f=new Fitxers();
    String dir=".data/";       // carpeta contenidora dels fitxers
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // NO HI HA CONSTRUCTORS EN UNA INTERFÍCIE
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Mètodes">
    // OBLIGUEM A QUE LES CLASSES QUE IMPLEMENTEN AQUSTA INTERFÍCIE
    // CREEN AQUESTS MÈTODES
    /**
     * Mètode per guardar un objecte vehicle
     *
     * @throws IOException Excepció d'E/S
     */
    void guardaVehicleFitxer(String rutaFitxer) throws IOException;

    /**
     * Mètode per retornar qualsevol objecte vehicle
     * ja retorna fins i tot formatat a la classe que pertany
     *
     * @param arxiu           ruta de l'arxiu
     * @param classe   classe de l'objecte a transformar
     * @param <T> classe de l'objecte a transformar
     * @return llista classe de l'objecte a transformar
     * @throws InterruptedException   excepció
     * @throws ClassNotFoundException excepció
     * @throws NoSuchFieldException   excepció
     * @throws IllegalAccessException excepció
     * @throws IOException            excepció
     */
    <T> Object retornaVehiclesEnLlista(String arxiu, T classe)
            throws InterruptedException, ClassNotFoundException,
            NoSuchFieldException, IllegalAccessException, IOException;

    /**
     * Genera una matrícula aleatoria amb le utilitació
     * del mètode generarClau de la llibreria LlibreriaComuna
     * A aquesta matrícula se li afegirà aleatoriament
     * unes sigles d'un país europeu.
     * <p>
     * Utilitzem polimorfisme, ja que aquest mètode està creat
     * en el pare, i reprogramat en classes filles
     *
     * @return marícula amb sigles pais
     */
    String generaMatricula();
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters">
    //getters (necessaris per a que des de la classe filla puga'm veure les dades (si volem clar)

    // Aquestes propietats son obligades en les classes
    // que implementen aquesta interfície
    String getMatricula();
    String getModel();
    Double getPotencia();

    //</editor-fold>

}
