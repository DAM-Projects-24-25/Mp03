package samso.duarte.pp_samso_duarte.controladors;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import samso.duarte.pp_samso_duarte.classes.Alerta;
import samso.duarte.pp_samso_duarte.classes.Taxi;

import java.io.IOException;

public class FAfegeixTaxi {


    // <editor-fold defaultstate="collapsed" desc="Components FXML">
    @FXML
    private AnchorPane FAfegeixLlibre;
    @FXML
    private TextField TFModel;
    @FXML
    private TextField TFPotencia;
    @FXML
    private TextField TFLlicencia;
    @FXML
    private TextField TFMatricula;
    @FXML
    private Button BGuarda;


    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Variables globals">
        Taxi tx=new Taxi();
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Mètodes">

    /**
     * Inicialització de les variables  del formulari
     *
     * @throws IOException excepció d'entrada/sortida
     */
    @FXML
    protected void initialize() {
        TFMatricula.setText(tx.generaMatricula());
    }

    /**
     * Botó per guardar la  informació dels text Fields
     *
     * @throws IOException execpció d'entrada sortida
     */
    @FXML
    public void BtGuarda() {

        // recollim dades:
        try {
            Double potencia=Double.parseDouble(TFPotencia.getText());
            String matricula=TFMatricula.getText();
            String model=TFModel.getText();
            String llicencia=TFLlicencia.getText();


            // construïm objecte, amb les dades de la classe pare (vehicle) i filla (taxi)
            Taxi tx=new Taxi(matricula, model, potencia, llicencia);


            /*utilitzem el mètode de llibre per escriure l'objecte al fitxer
            comproveu que el mètode està en vehicle. I no en taxi
            no obstant el pot utilitzar taxi, per l'herència declarada
            */
            tx.guardaVehicleFitxer(tx.getRutaFitxer());


            // buidem els TextBox
            buidaCamps();
        } catch (Exception e) {
            Alerta al=new Alerta("Algun camp no ha sigut introduït correctament","ERROR");
            al.alertaError();        }
    }

    /**
     * Buidem els comboBox del formulari
     */
    public void buidaCamps() {
        //Buidem camps
        TFPotencia.setText("");
        TFMatricula.setText(tx.generaMatricula());
        TFLlicencia.setText("");
        TFModel.setText("");
    }

    public void ompliCombo(){

    }


    //</editor-fold>

}
