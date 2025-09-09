package samso.duarte.pp_samso_duarte.metodes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * Bàsicament una reescriptura de la classe ObjectOutputStream però sense el mètode writeStreamHeader()
 * que reescriu la capçalera cada cop que l'utilitzem.
 *
 * D'aquesta manera puc escriure un objecte a un fitxer sense haver d'escriure la capçalera del fitxer cada cop
 * i em permetrà afegir objectes a un fitxer on ja hi han objectes prèviament
 *
 * S'utilitza en el mètode: escriuObjecteFitxer
 *
 * @see Fitxers#escriuObjecteFitxer(Object, String, boolean) escriuObjecteFitxer()
 *
 * @author vicent
 */
public class MeuObjecteOutputStream extends ObjectOutputStream{
    /**
     * Constructor que rep OutputStream
     * @param out sortida outputstream
     * @throws IOException Excepció d'E/S
     */
    public MeuObjecteOutputStream(OutputStream out) throws IOException
    {
        super(out);
    }

    /**
     * Constructor sense paràmetres
     * @throws IOException Excepció d'E/S
     * @throws SecurityException Excepció de seguretat
     */
    protected MeuObjecteOutputStream() throws IOException, SecurityException
    {
        super();
    }

    /**
     * Redefinició del mètode d'escriure la capçalera per a que no faci res.
     * @throws IOException Excepció d'E/S
     */
    protected void writeStreamHeader() throws IOException
    {
        // AQUEST MÈTODE ORIGINAL ESCRIU UNA CAPÇALERA
        // AQUI EL REESCRIC EN BLANC. NO FA RES
        // EL MÈTODE EXISTEIX, PERÒ NO ESCRIU LA CAPÇALERA
        // I ENS PERMETRÀ AFEGIR UN OBJECTE A UN FITXER DE FORMA NORMAL
    }

}

