package samso.duarte.pp_samso_duarte.controladors;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import samso.duarte.pp_samso_duarte.classes.Usuari;
import samso.duarte.pp_samso_duarte.metodes.Fitxers;

import java.io.IOException;
import java.util.List;

public class FMostrarUsuaris {

    public Button bttnModificar;
    public Button bttnEliminar;
    @FXML
    private TableView<Usuari> tableUsuaris;
    @FXML
    private TableColumn<Usuari, String> colNom;
    @FXML
    private TableColumn<Usuari, String> colCognom;
    @FXML
    private TableColumn<Usuari, String> colUsuari;
    @FXML
    private TableColumn<Usuari, String> colContrasenya;
    @FXML
    private CheckBox checkAdmin;

    private final ObservableList<Usuari> usuarisList = FXCollections.observableArrayList();
    private final Fitxers fitxers = new Fitxers();

    @FXML
    private void initialize() {
        // Assignar valors a cada columna de la taula
        colNom.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNom()));
        colCognom.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCognom()));
        colUsuari.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUsuari()));
        colContrasenya.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getContrasenya()));

        tableUsuaris.setItems(usuarisList);

        // Carregar usuaris normals per defecte
        carregarUsuaris(false);

        // Afegir event del CheckBox per alternar entre usuaris i administradors
        checkAdmin.setOnAction(event -> carregarUsuaris(checkAdmin.isSelected()));
    }

    private void carregarUsuaris(boolean mostrarAdmins) {
        usuarisList.clear();
        List<Usuari> usuaris;
        try {
            if (mostrarAdmins) {
                usuaris = (List<Usuari>) fitxers.retornaFitxerObjecteEnLlista(".data/administradors.dat", Usuari.class);
            } else {
                usuaris = (List<Usuari>) fitxers.retornaFitxerObjecteEnLlista(".data/usuaris.dat", Usuari.class);
            }

            if (usuaris != null) {
                usuarisList.addAll(usuaris);
            } else {
                mostrarError("No s'han pogut carregar els usuaris.");
            }
        } catch (IOException | ClassNotFoundException e) {
            mostrarError("Error en carregar els usuaris: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            mostrarError("L'operació ha estat interrompuda.");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            mostrarError("Error en accedir a les dades dels usuaris: " + e.getMessage());
        }
    }

    private void guardarUsuaris() {
        String fitxer = checkAdmin.isSelected() ? ".data/administradors.dat" : ".data/usuaris.dat";
        try {
            fitxers.escriuLlistaObjecteFitxer((List<Object>)(List<?>) usuarisList, fitxer);
        } catch (IOException e) {
            mostrarError("No s'han pogut guardar els usuaris: " + e.getMessage());
        }
    }

    @FXML
    private void afegirUsuari() {
        try {
            // Carregar el FXML de la finestra de registre (FAfegeixUsuaris.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/samso/duarte/pp_samso_duarte/FAfegeixUsuaris.fxml"));
            Parent root = loader.load();

            // Crear una nova finestra (Stage) per mostrar el registre
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Afegir Usuaris");  // Pot canviar el títol si vols
            stage.show();

            // Tancar la finestra actual si ho desitges (opcional)
            // Stage currentStage = (Stage) bttRegistre.getScene().getWindow();
            // currentStage.close();

        } catch (Exception e) {
            e.printStackTrace();
            // Si es produeix un error, mostrem un missatge d'error
            System.err.println("Error carregant la finestra de registre.");
        }
    }

    @FXML
    private void tancarFinestra() {
        Stage stage = (Stage) tableUsuaris.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void modificarUsuari() {
        Usuari usuariSeleccionat = tableUsuaris.getSelectionModel().getSelectedItem();
        if (usuariSeleccionat != null) {
            // Crear diàlegs per modificar cada camp de l'usuari
            TextInputDialog dialogNom = crearDialog("Modificar Nom", "Modificar el nom de l'usuari:", "Nom:", usuariSeleccionat.getNom());
            TextInputDialog dialogCognom = crearDialog("Modificar Cognom", "Modificar el cognom de l'usuari:", "Cognom:", usuariSeleccionat.getCognom());
            TextInputDialog dialogUsuari = crearDialog("Modificar Nom d'Usuari", "Modificar el nom d'usuari:", "Nom d'usuari:", usuariSeleccionat.getUsuari());
            TextInputDialog dialogContrasenya = crearDialog("Modificar Contrasenya", "Modificar la contrasenya de l'usuari:", "Contrasenya:", usuariSeleccionat.getContrasenya());

            // Obtenir els nous valors de l'usuari
            dialogNom.showAndWait().ifPresent(usuariSeleccionat::setNom);
            dialogCognom.showAndWait().ifPresent(usuariSeleccionat::setCognom);
            dialogUsuari.showAndWait().ifPresent(usuariSeleccionat::setUsuari);
            dialogContrasenya.showAndWait().ifPresent(usuariSeleccionat::setContrasenya);

            // Guardar els canvis i refrescar la taula
            guardarUsuaris(); // Actualitza el fitxer després de la modificació
            tableUsuaris.refresh(); // Refresca la taula
        } else {
            mostrarError("Selecciona un usuari per modificar.");
        }
    }

    // Mètode auxiliar per crear diàlegs
    private TextInputDialog crearDialog(String titol, String headerText, String contentText, String defaultValue) {
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(titol);
        dialog.setHeaderText(headerText);
        dialog.setContentText(contentText);
        return dialog;
    }

    @FXML
    private void eliminarUsuari() {
        Usuari usuariSeleccionat = tableUsuaris.getSelectionModel().getSelectedItem();
        if (usuariSeleccionat != null) {
            usuarisList.remove(usuariSeleccionat);
            guardarUsuaris(); // Actualitza el fitxer després de l'eliminació
        } else {
            mostrarError("Selecciona un usuari per eliminar.");
        }
    }

    private void mostrarError(String missatge) {
        Alert alert = new Alert(Alert.AlertType.ERROR, missatge);
        alert.showAndWait();
    }
}