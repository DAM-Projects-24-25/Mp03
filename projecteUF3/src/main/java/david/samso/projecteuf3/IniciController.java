package david.samso.projecteuf3;

import david.samso.projecteuf3.classes.Usuari;
import david.samso.projecteuf3.classes.Admin;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class IniciController {

    @FXML
    private TextField nomP;
    @FXML
    private TextField cognomP;
    @FXML
    private Button boto_inici;

    @FXML
    public void initialize() {}

    @FXML
    private void inici() {
        String nom = nomP.getText();
        String cognom = cognomP.getText();

        new Thread(() -> {
            boolean esUsuari = false;
            boolean esAdmin = false;

            try {
                Usuari us = new Usuari();
                List<Usuari> usuaris = us.retornaUsusari();
                for (Usuari usu : usuaris) {
                    if (usu.getNom().equals(nom) && usu.getCognom().equals(cognom)) {
                        esUsuari = true;
                        break;
                    }
                }

                Admin ad = new Admin();
                List<Admin> admins = ad.retornaAdmin();
                for (Admin admin : admins) {
                    if (admin.getNom().equals(nom) && admin.getCognom().equals(cognom)) {
                        esAdmin = true;
                        break;
                    }
                }

                boolean finalEsUsuari = esUsuari;
                boolean finalEsAdmin = esAdmin;
                Platform.runLater(() -> {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        Stage stage = (Stage) boto_inici.getScene().getWindow();
                        if (finalEsAdmin) {
                            loader.setLocation(getClass().getResource("admin.fxml"));
                        } else if (finalEsUsuari) {
                            loader.setLocation(getClass().getResource("producte.fxml"));
                        } else {
                            loader.setLocation(getClass().getResource("inici.fxml"));
                            showAlert("Usuari no trobat", "No s'ha trobat cap usuari o administrador amb aquests credencials.");
                        }
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        showAlert("Error de càrrega", "Hi ha hagut un problema carregant la pàgina.");
                    }
                });
            } catch (IOException | InterruptedException | ClassNotFoundException e) {
                e.printStackTrace();
                Platform.runLater(() -> showAlert("Error de lectura", "Hi ha hagut un problema llegint les dades."));
            }
        }).start();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
