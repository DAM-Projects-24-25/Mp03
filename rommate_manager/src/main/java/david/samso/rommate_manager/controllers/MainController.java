package david.samso.rommate_manager.controllers;

import david.samso.rommate_manager.bbdd.PostgreSQL;
import david.samso.rommate_manager.bbdd_conect.DataBaseConnection;
import david.samso.rommate_manager.utils.AlertMessage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController {
    @FXML private ListView<String> roommateListView;
    @FXML private ListView<String> apartmentListView;
    @FXML private ListView<String> expenseListView;
    @FXML private ListView<String> taskListView;

    @FXML private Button addRoommateButton;
    @FXML private Button addApartmentButton;
    @FXML private Button addExpenseButton;
    @FXML private Button addTaskButton;

    private final PostgreSQL postgres;

    public MainController() {
        this.postgres = DataBaseConnection.getPostgreSQLConnection();
    }

    @FXML
    public void initialize() {
        // Carregar dades a les ListViews
        refreshAll();

        // Assignar accions als botons
        addRoommateButton.setOnAction(e -> {
            openWindow("RoommateView.fxml", "Afegeix Roommate");
            loadRoommates();
        });

        addApartmentButton.setOnAction(e -> {
            openWindow("ApartmentView.fxml", "Afegeix Apartament");
            loadApartments();
        });

        addExpenseButton.setOnAction(e -> {
            openWindow("ExpenseView.fxml", "Afegeix Despesa");
            loadExpenses();
        });

        addTaskButton.setOnAction(e -> {
            openWindow("TaskView.fxml", "Afegeix Tasca");
            loadTasks();
        });
    }

    private void refreshAll() {
        loadRoommates();
        loadApartments();
        loadExpenses();
        loadTasks();
    }

    private void loadRoommates() {
        try {
            roommateListView.getItems().clear();
            ResultSet rs = postgres.executaSQLSelect("SELECT * FROM roommates");
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                roommateListView.getItems().add(name + " (" + email + ")");
            }
        } catch (SQLException e) {
            AlertMessage.showError("Error al cargar los roommates: " + e.getMessage());
        }
    }

    private void loadApartments() {
        try {
            apartmentListView.getItems().clear();
            ResultSet rs = postgres.executaSQLSelect("SELECT * FROM apartments");
            while (rs.next()) {
                String address = rs.getString("address");
                double rent = rs.getDouble("rent");
                apartmentListView.getItems().add(address + " - " + rent + "€/mes");
            }
        } catch (SQLException e) {
            AlertMessage.showError("Error al cargar los apartamentos: " + e.getMessage());
        }
    }

    private void loadExpenses() {
        try {
            expenseListView.getItems().clear();
            ResultSet rs = postgres.executaSQLSelect("SELECT * FROM expenses");
            while (rs.next()) {
                String description = rs.getString("description");
                double amount = rs.getDouble("amount");
                String date = rs.getDate("date").toString();
                expenseListView.getItems().add(description + " - " + amount + "€ (" + date + ")");
            }
        } catch (SQLException e) {
            AlertMessage.showError("Error al cargar las despesas: " + e.getMessage());
        }
    }

    private void loadTasks() {
        try {
            taskListView.getItems().clear();
            ResultSet rs = postgres.executaSQLSelect("SELECT * FROM tasks");
            while (rs.next()) {
                String title = rs.getString("title");
                boolean completed = rs.getBoolean("completed");
                taskListView.getItems().add(title + (completed ? " ✔" : " ❌"));
            }
        } catch (SQLException e) {
            AlertMessage.showError("Error al cargar las tareas: " + e.getMessage());
        }
    }

    private void openWindow(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/david/samso/rommate_manager/view/" + fxmlPath));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            AlertMessage.showError("Error al abrir la ventana: " + e.getMessage());
        }
    }
}
