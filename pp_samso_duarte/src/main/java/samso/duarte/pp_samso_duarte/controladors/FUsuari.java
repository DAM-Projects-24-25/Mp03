package samso.duarte.pp_samso_duarte.controladors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import samso.duarte.pp_samso_duarte.classes.Usuari;
import samso.duarte.pp_samso_duarte.metodes.Fitxers;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class FUsuari {

    public Hyperlink linkRegistrar;
    @FXML
    private TextField fieldUsuari;
    @FXML
    private PasswordField fieldContra;
    @FXML
    private Button btnLogin; // Botó d'inici de sessió


    @FXML
    private void initialize() {
        // Inicialització si es necessària
    }

    @FXML
    private void iniciarSessio() {
        String usuari = fieldUsuari.getText().trim();
        String contrasenya = fieldContra.getText().trim();

        if (usuari.isEmpty() || contrasenya.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Error de Validació", "Tots els camps són obligatoris.");
            return;
        }

        Usuari usuariTrobat = validarCredencials(usuari, contrasenya);
        if (usuariTrobat != null) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Inici de Sessió", "Benvingut, " + usuariTrobat.getNom() + "!");
            obrirFinestrasegonsTipusUsuari(usuariTrobat);
        } else {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Credencials incorrectes.");
        }
    }

    private void obrirFinestrasegonsTipusUsuari(Usuari usuari) {
        try {
            String fxmlFile;
            if (usuari.isAdministrador()) {
                fxmlFile = "/samso/duarte/pp_samso_duarte/FMostrarUsuaris.fxml";
            } else {
                fxmlFile = "/samso/duarte/pp_samso_duarte/FPrincipal.fxml";
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(usuari.isAdministrador() ? "Panell d'Administrador" : "Finestra Principal");
            stage.show();

            // Tancar la finestra de login
            Stage loginStage = (Stage) btnLogin.getScene().getWindow();
            loginStage.close();

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No s'ha pogut obrir la finestra corresponent.");
        }
    }

    private Usuari validarCredencials(String usuari, String contrasenya) {
        Usuari usuariTrobat = carregarUsuariDeFitxer(new File(".data/administradors.dat"), usuari, contrasenya);
        if (usuariTrobat == null) {
            usuariTrobat = carregarUsuariDeFitxer(new File(".data/usuaris.dat"), usuari, contrasenya);
        }
        return usuariTrobat;
    }

    private Usuari carregarUsuariDeFitxer(File fitxer, String usuari, String contrasenya) {
        if (fitxer.exists()) {
            try {
                // Ús del mètode retornaFitxerObjecteEnLlista
                Fitxers fitxersUtil = new Fitxers();
                List<Usuari> usuaris = (List<Usuari>) fitxersUtil.retornaFitxerObjecteEnLlista(fitxer.getAbsolutePath(), Usuari.class);

                for (Usuari u : usuaris) {
                    if (u.getUsuari().equals(usuari) && u.getContrasenya().equals(contrasenya)) {
                        u.setAdministrador(fitxer.getName().equals("administradors.dat"));
                        return u;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlerta(Alert.AlertType.ERROR, "Error", "No s'ha pogut carregar l'usuari o usuaris.");
            }
        }
        return null;
    }


    @FXML
    private void registrarUsuari() {
        try {
            // Carregar el FXML de la finestra de registre (FAfegeixUsuaris.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/samso/duarte/pp_samso_duarte/FAfegeixUsuaris.fxml"));
            Parent root = loader.load();

            // Crear una nova finestra (Stage) per mostrar el registre
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registrar Usuari");  // Pot canviar el títol si vols
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            // Si es produeix un error, mostrem un missatge d'error
            System.err.println("Error carregant la finestra de registre.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipus, String titol, String missatge) {
        Alert alert = new Alert(tipus);
        alert.setTitle(titol);
        alert.setHeaderText(null);
        alert.setContentText(missatge);
        alert.showAndWait();
    }
}