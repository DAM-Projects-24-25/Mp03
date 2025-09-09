package samso.duarte.pp_samso_duarte.controladors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import samso.duarte.pp_samso_duarte.classes.Usuari;
import samso.duarte.pp_samso_duarte.metodes.Fitxers;

import java.io.IOException;

public class FAfegeixUsuaris {

    public Hyperlink hyperMostrarU;
    public Button bttnGuardar;
    public Text txtNom;
    public Text txtCognom;
    public Text txtUsuari;
    public Text txtContrasenya;
    @FXML private TextField fieldNom;
    @FXML private TextField fieldCognom;
    @FXML private TextField fieldUsuari;
    @FXML private TextField fieldContrasenya;
    @FXML private CheckBox checkAdmin;
    @FXML private Text txtUsuaristtl;

    // Mètode per mostrar un alerta
    private void alerta(String títol, String missatge, Alert.AlertType tipus) {
        Alert alert = new Alert(tipus);
        alert.setTitle(títol);
        alert.setContentText(missatge);
        alert.showAndWait();
    }

    // Mètode per guardar un usuari
    @FXML
    private void guardarUsuari(ActionEvent event) {
        String nom = fieldNom.getText().trim();
        String cognom = fieldCognom.getText().trim();
        String usuari = fieldUsuari.getText().trim();
        String contrasenya = fieldContrasenya.getText().trim();
        boolean esAdministrador = checkAdmin.isSelected();

        // Validació dels camps
        if (nom.isEmpty() || cognom.isEmpty() || usuari.isEmpty() || contrasenya.isEmpty()) {
            alerta("Error", "Tots els camps són obligatoris!", Alert.AlertType.ERROR);
            return;
        }

        // Creació de l'usuari
        Usuari nouUsuari = new Usuari(nom, cognom, usuari, contrasenya, esAdministrador);

        // Determina el fitxer a utilitzar en funció de si és administrador o no
        String fitxer = esAdministrador ? ".data/administradors.dat" : ".data/usuaris.dat";

        try {
            // Guardar l'usuari al fitxer corresponent
            Fitxers fitxers = new Fitxers();
            fitxers.escriuObjecteFitxer(nouUsuari, fitxer, true);  // true per afegir-lo al final del fitxer

            alerta("Èxit", "Usuari guardat correctament!", Alert.AlertType.INFORMATION);
            // Neteja els camps després d'afegir l'usuari
            netejaCamps();
        } catch (Exception e) {
            alerta("Error", "No s'ha pogut guardar l'usuari: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    // Mètode per netejar els camps del formulari
    private void netejaCamps() {
        fieldNom.clear();
        fieldCognom.clear();
        fieldUsuari.clear();
        fieldContrasenya.clear();
        checkAdmin.setSelected(false);
    }

    // Mètode per mostrar la llista d'usuaris
    public void mostrarUsuaris(ActionEvent event) {
        try {
            // Carregar el FXML de la vista de mostrar usuaris
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/samso/duarte/pp_samso_duarte/FMostrarUsuaris.fxml"));
            AnchorPane root = loader.load();

            // Crear la nova escena i afegir-la a la finestra
            Stage stage = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // Si es produeix un error, mostrar un missatge d'error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al carregar la vista d'usuaris");
            alert.setContentText("No es va poder carregar la vista d'usuaris. Intenta-ho més tard.");
            alert.showAndWait();
        }
    }
}
