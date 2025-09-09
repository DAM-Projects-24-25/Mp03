package david.samso.projecteuf3.classes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MeuObjectOutputStream extends ObjectOutputStream {
    //<editor-fold desc="Constructors">
    /* Constructor que rep OutputStream */
    public MeuObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }


    /* Constructor sense paràmetres */
    protected MeuObjectOutputStream() throws IOException, SecurityException {
        super();
    }

    /**
     * writeStreamHeader.
     * 1-AQUEST MÈTODE ORIGINAL ESCRIU UNA CAPÇALERA,AQUÍ ES REESCRIU EN BLANC, NO FA RES
     * EL MÈTODE EXISTEIX, PERÒ NO ESCRIU LA CAPÇALERA I ENS PERMETRÀ AFEGIR UN OBJECTE A UN FITXER DE FORMA NORMAL
     */
    protected void writeStreamHeader() throws IOException {
        //1
    }
    //</editor-fold>
}
