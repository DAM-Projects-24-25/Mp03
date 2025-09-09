package samso.duarte.pp_samso_duarte.controladors;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import samso.duarte.pp_samso_duarte.classes.Autobus;

import java.io.IOException;
import java.util.List;


public class FMostrarAutobus {


    // <editor-fold defaultstate="collapsed" desc="Components FXML">

    @FXML
    private TableView TVPantalla;



    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Variables globals">


    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Mètodes">

    @FXML
    protected void initialize() throws IOException, ClassNotFoundException, InterruptedException, NoSuchFieldException, IllegalAccessException {

        Autobus au= new Autobus();
        //TAPantalla.setText("");

        List<Autobus> llistaAutobussos=(List<Autobus>) au.retornaVehiclesEnLlista(au.getRutaFitxer(),Autobus.class);
        llistaAutobussos.sort(Autobus::compareTo);  // mètode compareTo en Autobus (interficie comparable)

        mostraAutobussos(llistaAutobussos);
    }


    /**
     * Omplim una TableView amb les dades de la Llista d'Objectes
     *
     * @param tx Llista d'Autobusos que han sigut recollits del fitxer autobusos.dat
     */
    private void ompliTaula(List<Autobus> tx) {

        // Necessitem una Llista Observable de la classe
        ObservableList<Autobus> dades = FXCollections.observableArrayList();

        //Omplim aquesta llistaObservable amb les dades del fitxer. (Llista d'Autobusos)
        for (Autobus au : tx) {
            dades.add(au);
        }

        // Creem els noms de les columnes de la taula
        TableColumn columnMatricula = new TableColumn("Matrícula");     // Nom de la columna
        columnMatricula.setMinWidth(10);                                // podem ajustar el tamany
        TableColumn columnPlaces = new TableColumn("Places");
        TableColumn columnModel = new TableColumn("Model");
        TableColumn columnPotencia = new TableColumn("Potència");

        // Posem els valors a les columnes. Al final hem de posar el nom de l'atribut definit a la classe
        columnMatricula.setCellValueFactory(new PropertyValueFactory<Autobus, String>("matricula"));
        columnPlaces.setCellValueFactory(new PropertyValueFactory<Autobus, Integer>("numPlaces"));
        columnModel.setCellValueFactory(new PropertyValueFactory<Autobus, String>("model"));
        columnPotencia.setCellValueFactory(new PropertyValueFactory<Autobus, Double>("potencia"));

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
    private void mostraAutobussos(List<Autobus> tx) {

        /*Amb tableView*/
        ompliTaula(tx);


//            /*Amb textArea*/
//        int i;
//        for (i = 0; i < tx.size(); i++) {
//            TAPantalla.setText(TAPantalla.getText() +
//                    "\nMatrícula: " + tx.get(i).getMatricula() +
//                    "\nPlaces: " + tx.get(i).getNumPlaces() +
//                    "\nModel: " + tx.get(i).getModel() +
//                    "\nPotència: " + tx.get(i).getPotencia() + "\n*******\n ");
//        }
    }

    //</editor-fold>

}
