package david.samso.rommate_manager.controllers;

import david.samso.rommate_manager.bbdd.PostgreSQL;
import david.samso.rommate_manager.bbdd_conect.DataBaseConnection;
import david.samso.rommate_manager.models.Task;
import david.samso.rommate_manager.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskController {
    @FXML private TextField titleField;
    @FXML private TextArea descriptionField;
    @FXML private CheckBox completedCheckBox;
    @FXML private TableView<Task> taskTable;
    @FXML private TableColumn<Task, Integer> idColumn;
    @FXML private TableColumn<Task, String> titleColumn;
    @FXML private TableColumn<Task, String> descriptionColumn;
    @FXML private TableColumn<Task, Boolean> completedColumn;

    private final PostgreSQL postgres;
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();

    public TaskController() {
        this.postgres = DataBaseConnection.getPostgreSQLConnection();
    }

    @FXML
    private void initialize() {
        setupTable();
        loadTasks();
    }

    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        completedColumn.setCellValueFactory(new PropertyValueFactory<>("completed"));
        taskTable.setItems(tasks);
    }

    private void loadTasks() {
        try {
            tasks.clear();
            ResultSet rs = postgres.executaSQLSelect("SELECT * FROM tasks");
            while (rs.next()) {
                tasks.add(new Task(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getBoolean("completed")
                ));
            }
        } catch (SQLException e) {
            AlertMessage.showError("Error al cargar las tareas: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdd() {
        String title = titleField.getText();
        String description = descriptionField.getText();
        boolean completed = completedCheckBox.isSelected();

        String query = "INSERT INTO tasks (title, description, completed) VALUES ('" + title + "', '" +
                     description + "', " + completed + ")";
        postgres.executaSQL(query);
        clearFields();
        loadTasks();
        AlertMessage.showSuccess("Tarea añadida correctamente");
    }

    @FXML
    private void handleDelete() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            String query = "DELETE FROM tasks WHERE id = " + selectedTask.getId();
            postgres.executaSQL(query);
            loadTasks();
            AlertMessage.showSuccess("Tarea eliminada correctamente");
        } else {
            AlertMessage.showWarning("Por favor, selecciona una tarea para eliminar");
        }
    }

    @FXML
    private void handleUpdate() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            String title = titleField.getText();
            String description = descriptionField.getText();
            boolean completed = completedCheckBox.isSelected();

            String query = "UPDATE tasks SET title = '" + title + "', description = '" + description +
                         "', completed = " + completed + " WHERE id = " + selectedTask.getId();
            postgres.executaSQL(query);
            loadTasks();
            clearFields();
            AlertMessage.showSuccess("Tarea actualizada correctamente");
        } else {
            AlertMessage.showWarning("Por favor, selecciona una tarea para actualizar");
        }
    }

    private void clearFields() {
        titleField.clear();
        descriptionField.clear();
        completedCheckBox.setSelected(false);
    }
}
