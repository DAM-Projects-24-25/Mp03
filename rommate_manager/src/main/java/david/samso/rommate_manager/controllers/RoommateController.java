package david.samso.rommate_manager.controllers;

import david.samso.rommate_manager.bbdd.PostgreSQL;
import david.samso.rommate_manager.bbdd_conect.DataBaseConnection;
import david.samso.rommate_manager.models.Roommate;
import david.samso.rommate_manager.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoommateController {
    @FXML private TextField nameField;
    @FXML private TextField ageField;
    @FXML private TextField emailField;
    @FXML private TableView<Roommate> roommateTable;
    @FXML private TableColumn<Roommate, Integer> idColumn;
    @FXML private TableColumn<Roommate, String> nameColumn;
    @FXML private TableColumn<Roommate, Integer> ageColumn;
    @FXML private TableColumn<Roommate, String> emailColumn;

    private final PostgreSQL postgres;
    private final ObservableList<Roommate> roommates = FXCollections.observableArrayList();

    public RoommateController() {
        this.postgres = DataBaseConnection.getPostgreSQLConnection();
    }

    @FXML
    private void initialize() {
        setupTable();
        loadRoommates();
    }

    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roommateTable.setItems(roommates);
    }

    private void loadRoommates() {
        try {
            roommates.clear();
            ResultSet rs = postgres.executaSQLSelect("SELECT * FROM roommates");
            while (rs.next()) {
                roommates.add(new Roommate(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            AlertMessage.showError("Error al cargar los roommates: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdd() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String email = emailField.getText();

            String query = "INSERT INTO roommates (name, age, email) VALUES ('" + name + "', " + age + ", '" + email + "')";
            postgres.executaSQL(query);
            clearFields();
            loadRoommates();
            AlertMessage.showSuccess("Roommate añadido correctamente");
        } catch (NumberFormatException e) {
            AlertMessage.showError("La edad debe ser un número entero");
        }
    }

    @FXML
    private void handleDelete() {
        Roommate selectedRoommate = roommateTable.getSelectionModel().getSelectedItem();
        if (selectedRoommate != null) {
            String query = "DELETE FROM roommates WHERE id = " + selectedRoommate.getId();
            postgres.executaSQL(query);
            loadRoommates();
            AlertMessage.showSuccess("Roommate eliminado correctamente");
        } else {
            AlertMessage.showWarning("Por favor, selecciona un roommate para eliminar");
        }
    }

    @FXML
    private void handleUpdate() {
        Roommate selectedRoommate = roommateTable.getSelectionModel().getSelectedItem();
        if (selectedRoommate != null) {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String email = emailField.getText();

                String query = "UPDATE roommates SET name = '" + name + "', age = " + age + 
                             ", email = '" + email + "' WHERE id = " + selectedRoommate.getId();
                postgres.executaSQL(query);
                loadRoommates();
                clearFields();
                AlertMessage.showSuccess("Roommate actualizado correctamente");
            } catch (NumberFormatException e) {
                AlertMessage.showError("La edad debe ser un número entero");
            }
        } else {
            AlertMessage.showWarning("Por favor, selecciona un roommate para actualizar");
        }
    }

    private void clearFields() {
        nameField.clear();
        ageField.clear();
        emailField.clear();
    }
}
