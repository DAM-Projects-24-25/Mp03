package david.samso.rommate_manager.controllers;

import david.samso.rommate_manager.bbdd.PostgreSQL;
import david.samso.rommate_manager.bbdd_conect.DataBaseConnection;
import david.samso.rommate_manager.models.Apartment;
import david.samso.rommate_manager.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmentController {
    @FXML private TextField addressField;
    @FXML private TextField rentField;
    @FXML private TableView<Apartment> apartmentTable;
    @FXML private TableColumn<Apartment, Integer> idColumn;
    @FXML private TableColumn<Apartment, String> addressColumn;
    @FXML private TableColumn<Apartment, Double> rentColumn;

    private final PostgreSQL postgres;
    private final ObservableList<Apartment> apartments = FXCollections.observableArrayList();

    public ApartmentController() {
        this.postgres = DataBaseConnection.getPostgreSQLConnection();
    }

    @FXML
    private void initialize() {
        setupTable();
        loadApartments();
    }

    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        rentColumn.setCellValueFactory(new PropertyValueFactory<>("rent"));
        apartmentTable.setItems(apartments);
    }

    private void loadApartments() {
        try {
            apartments.clear();
            ResultSet rs = postgres.executaSQLSelect("SELECT * FROM apartments");
            while (rs.next()) {
                apartments.add(new Apartment(
                    rs.getInt("id"),
                    rs.getString("address"),
                    rs.getDouble("rent")
                ));
            }
        } catch (SQLException e) {
            AlertMessage.showError("Error al cargar los apartamentos: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdd() {
        try {
            String address = addressField.getText();
            double rent = Double.parseDouble(rentField.getText());
            
            String query = "INSERT INTO apartments (address, rent) VALUES ('" + address + "', " + rent + ")";
            postgres.executaSQL(query);
            clearFields();
            loadApartments();
            AlertMessage.showSuccess("Apartamento añadido correctamente");
        } catch (NumberFormatException e) {
            AlertMessage.showError("El precio del alquiler debe ser un número");
        }
    }

    @FXML
    private void handleDelete() {
        Apartment selectedApartment = apartmentTable.getSelectionModel().getSelectedItem();
        if (selectedApartment != null) {
            String query = "DELETE FROM apartments WHERE id = " + selectedApartment.getId();
            postgres.executaSQL(query);
            loadApartments();
            AlertMessage.showSuccess("Apartamento eliminado correctamente");
        } else {
            AlertMessage.showWarning("Por favor, selecciona un apartamento para eliminar");
        }
    }

    @FXML
    private void handleUpdate() {
        Apartment selectedApartment = apartmentTable.getSelectionModel().getSelectedItem();
        if (selectedApartment != null) {
            try {
                String address = addressField.getText();
                double rent = Double.parseDouble(rentField.getText());

                String query = "UPDATE apartments SET address = '" + address + "', rent = " + rent + 
                             " WHERE id = " + selectedApartment.getId();
                postgres.executaSQL(query);
                loadApartments();
                clearFields();
                AlertMessage.showSuccess("Apartamento actualizado correctamente");
            } catch (NumberFormatException e) {
                AlertMessage.showError("El precio del alquiler debe ser un número");
            }
        } else {
            AlertMessage.showWarning("Por favor, selecciona un apartamento para actualizar");
        }
    }

    private void clearFields() {
        addressField.clear();
        rentField.clear();
    }
}
