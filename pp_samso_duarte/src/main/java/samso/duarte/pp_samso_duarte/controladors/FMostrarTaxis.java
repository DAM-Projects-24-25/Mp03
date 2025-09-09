package samso.duarte.pp_samso_duarte.controladors;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import samso.duarte.pp_samso_duarte.classes.Taxi;

import java.io.IOException;
import java.util.List;


public class FMostrarTaxis {


    // <editor-fold defaultstate="collapsed" desc="Components FXML">

    @FXML
    //private TextArea TAPantalla;
    private TableView TVPantalla;


    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Variables globals">


    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Mètodes">

    @FXML
    protected void initialize() throws IOException, ClassNotFoundException, InterruptedException, NoSuchFieldException, IllegalAccessException {

        Taxi tx= new Taxi();
      //  TAPantalla.setText("");

        List<Taxi> llistaTaxis=(List<Taxi>) tx.retornaVehiclesEnLlista(tx.getRutaFitxer(),Taxi.class);
        llistaTaxis.sort(Taxi::compareTo);  // mètode compareTo en Autobus (interficie comparable)
        mostraTaxis(llistaTaxis);
    }

    /**
     * Omplim una TableView amb les dades de la Llista d'Objectes
     *
     * @param tx Llista d'Autobusos que han sigut recollits del fitxer autobusos.dat
     */
    private void ompliTaula(List<Taxi> tx) {

        // Necessitem una Llista Observable de la classe
        ObservableList<Taxi> dades = FXCollections.observableArrayList();

        //Omplim aquesta llistaObservable amb les dades del fitxer. (Llista d'Autobusos)
        for (Taxi au : tx) {
            dades.add(au);
        }

        // Creem els noms de les columnes de la taula
        TableColumn columnMatricula = new TableColumn("Matrícula");     // Nom de la columna
        columnMatricula.setMinWidth(10);                                // podem ajustar el tamany
        TableColumn columnPlaces = new TableColumn("numeroLlicencia");
        TableColumn columnModel = new TableColumn("Model");
        TableColumn columnPotencia = new TableColumn("Potència");

        // Posem els valors a les columnes. Al final hem de posar el nom de l'atribut definit a la classe
        columnMatricula.setCellValueFactory(new PropertyValueFactory<Taxi, String>("matricula"));
        columnPlaces.setCellValueFactory(new PropertyValueFactory<Taxi, String>("numeroLlicencia"));
        columnModel.setCellValueFactory(new PropertyValueFactory<Taxi, String>("model"));
        columnPotencia.setCellValueFactory(new PropertyValueFactory<Taxi, Double>("potencia"));

        // Omplim el TableView amb les dades d'abans
        TVPantalla.setItems(dades);
        TVPantalla.getColumns().addAll(columnMatricula, columnPlaces, columnModel, columnPotencia);

    }


    /**
     * Mostrarà en el RichTextBox
     * El contingut de tot el fitxer de taxis
     * extret del fitxer on estan tots els taxis guardats
     * que li passarem per paràmetre
     *
     * @param tx Llista de tipus taxis
     */
    private void mostraTaxis(List<Taxi> tx) {

        ompliTaula(tx);

//        int i;
//        for (i = 0; i < tx.size(); i++) {
//            TAPantalla.setText(TAPantalla.getText() +
//                    "\nMatrícula: " + tx.get(i).getMatricula() +
//                    "\nLlicència: " + tx.get(i).getNumeroLlicencia() +
//                    "\nModel: " + tx.get(i).getModel() +
//                    "\nPotència: " + tx.get(i).getPotencia() + "\n*******\n ");
//        }
//


    }


    //</editor-fold>

}
