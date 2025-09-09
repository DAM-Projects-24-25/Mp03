package david.samso.projecteuf3;

import david.samso.projecteuf3.classes.Admin;
import david.samso.projecteuf3.classes.Usuari;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class AdminController {

    @FXML
    private TextArea textAreaUsuari;
    @FXML
    private TextArea textAreaAdmin;
    @FXML
    private Button registreUsuariButton;
    @FXML
    private AnchorPane panelUsuari;
    @FXML
    private Button mostraUsuariButton;
    @FXML
    private TextField nomAdmin;
    @FXML
    private TextField cognomAdmin;
    @FXML
    private Button registraAdminButton;
    @FXML
    private AnchorPane panelAdmin;
    @FXML
    private Button mostraAdminButton;
    @FXML
    private TextField cognomUsuari;
    @FXML
    private TextField nomUsuari;

    private static Admin ad = new Admin();
    private static Usuari us = new Usuari();

    public void initialize() {}

    @FXML
    public void registraUsuari() {
        String nom = nomUsuari.getText().trim();
        String cognom = cognomUsuari.getText().trim();

        if (nom.isEmpty() || cognom.isEmpty()) {
            System.out.println("Cal omplir tots els camps");
            return;
        }

        try {
            Usuari nouUsuari = new Usuari(nom, cognom);
            nouUsuari.guardaUsuari();
            System.out.println("Usuari guardat correctament");
        } catch (IOException e) {
            System.out.println("Error d'entrada/sortida");
            e.printStackTrace();
        }
    }

    @FXML
    public void registraAdmin() {
        String nom = nomAdmin.getText().trim();
        String cognom = cognomAdmin.getText().trim();

        if (nom.isEmpty() || cognom.isEmpty()) {
            System.out.println("Cal omplir tots els camps");
            return;
        }

        try {
            Admin nouAdmin = new Admin(nom, cognom);
            nouAdmin.guardaAdmin();
            System.out.println("Administrador guardat correctament");
        }  catch (IOException e) {
            System.out.println("Error d'entrada/sortida");
            e.printStackTrace();
        }
    }

    @FXML
    public void mostraUsuaris() {
        try {
            List<Usuari> usuariList = us.retornaUsusari();
            StringBuilder text = new StringBuilder();

            for (Usuari usu : usuariList) {
                text.append(usu.getNom()).append(";").append(usu.getCognom()).append(";").append("\n");
            }
            textAreaUsuari.setText(text.toString());
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            System.out.println("Error en mostrar usuaris");
            e.printStackTrace();
        }
    }

    @FXML
    public void mostraAdmins() {
        try {
            List<Admin> adminList = ad.retornaAdmin();
            StringBuilder text = new StringBuilder();

            for (Admin admin : adminList) {
                text.append(admin.getNom()).append(";").append(admin.getCognom()).append(";").append("\n");
            }
            textAreaAdmin.setText(text.toString());
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            System.out.println("Error en mostrar administradors");
            e.printStackTrace();
        }
    }
}
