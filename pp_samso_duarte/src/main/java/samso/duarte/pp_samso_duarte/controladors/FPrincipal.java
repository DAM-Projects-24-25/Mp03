package samso.duarte.pp_samso_duarte.controladors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import samso.duarte.pp_samso_duarte.FAplicacioPrincipal;
import samso.duarte.pp_samso_duarte.classes.Alerta;
import samso.duarte.pp_samso_duarte.classes.Autobus;
import samso.duarte.pp_samso_duarte.classes.Taxi;
import samso.duarte.pp_samso_duarte.metodes.Fitxers;


public class FPrincipal {


    // <editor-fold defaultstate="collapsed" desc="components Formulari">

    @FXML
    private MenuItem MIAfegirTaxi;
    @FXML
    private MenuItem MIMostrarTaxi;
    @FXML
    private MenuItem MIAfegirAutobus;
    @FXML
    private MenuItem MIMostrarAutor;


    //</editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Variables globals Formulari Principal">

    Fitxers f = new Fitxers();
    Taxi tx = new Taxi();
    Autobus au = new Autobus();

    //</editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Mètodes del formulari">

    /**
     * Obrim el formulari que passem per paràmetre. Amb el titol també passat per paràmetre
     *
     * @param formulari Formulari a obrir
     * @param titol     Títol del formulari
     */
    private void FGeneric(String formulari, String titol) {
        try {


            FXMLLoader fxmlLoader = new FXMLLoader(FAplicacioPrincipal.class.getResource(formulari));


            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            // stage.initStyle((StageStyle.TRANSPARENT));
            stage.setTitle(titol);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();

        } catch (Exception e) {

        }
    }


    // Taxi
    @FXML
    public void afegirTaxi() {

        FGeneric("FAfegeixTaxi.fxml", "Afegir Taxis");
    }

    @FXML
    public void mostrarTaxi() {
        if (f.existeix(tx.getRutaFitxer()))
            FGeneric("FMostrarTaxis.fxml", "Llistat de taxis");
        else{
            Alerta al=new Alerta("No existeix el fitxer: \n" + tx.getRutaFitxer(),"ERROR");
            al.alertaError();
        }
    }


    // Autobús
    @FXML
    public void afegirAutobus() {
        FGeneric("FAfegeixAutobus.fxml", "Afegir Autobus");
    }

    @FXML
    public void mostarAutobus() {
        if (f.existeix(au.getRutaFitxer()))
            FGeneric("FMostrarAutobus.fxml", "Llista d'autobussos");
        else{
            Alerta al=new Alerta("No existeix el fitxer: \n" + au.getRutaFitxer(),"ERROR");
            al.alertaError();
        }
    }


    //</editor-fold>


}
