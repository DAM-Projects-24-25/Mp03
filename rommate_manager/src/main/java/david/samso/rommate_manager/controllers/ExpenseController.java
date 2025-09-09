package david.samso.rommate_manager.controllers;

import david.samso.rommate_manager.bbdd.PostgreSQL;
import david.samso.rommate_manager.bbdd_conect.DataBaseConnection;
import david.samso.rommate_manager.models.Expense;
import david.samso.rommate_manager.utils.AlertMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ExpenseController {
    @FXML private TextField descriptionField;
    @FXML private TextField amountField;
    @FXML private DatePicker datePicker;
    @FXML private TableView<Expense> expenseTable;
    @FXML private TableColumn<Expense, Integer> idColumn;
    @FXML private TableColumn<Expense, String> descriptionColumn;
    @FXML private TableColumn<Expense, Double> amountColumn;
    @FXML private TableColumn<Expense, LocalDate> dateColumn;

    private final PostgreSQL postgres;
    private final ObservableList<Expense> expenses = FXCollections.observableArrayList();

    public ExpenseController() {
        this.postgres = DataBaseConnection.getPostgreSQLConnection();
    }

    @FXML
    private void initialize() {
        setupTable();
        loadExpenses();
        datePicker.setValue(LocalDate.now());
    }

    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        expenseTable.setItems(expenses);
    }

    private void loadExpenses() {
        try {
            expenses.clear();
            ResultSet rs = postgres.executaSQLSelect("SELECT * FROM expenses");
            while (rs.next()) {
                expenses.add(new Expense(
                    rs.getInt("id"),
                    rs.getString("description"),
                    rs.getDouble("amount"),
                    rs.getDate("date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            AlertMessage.showError("Error al cargar las despesas: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdd() {
        try {
            String description = descriptionField.getText();
            double amount = Double.parseDouble(amountField.getText());
            LocalDate date = datePicker.getValue();

            if (date == null) {
                date = LocalDate.now();
            }

            String query = "INSERT INTO expenses (description, amount, date) VALUES ('" + description + "', " + 
                         amount + ", '" + date + "')";
            postgres.executaSQL(query);
            clearFields();
            loadExpenses();
            AlertMessage.showSuccess("Despesa añadida correctamente");
        } catch (NumberFormatException e) {
            AlertMessage.showError("El importe debe ser un número");
        }
    }

    @FXML
    private void handleDelete() {
        Expense selectedExpense = expenseTable.getSelectionModel().getSelectedItem();
        if (selectedExpense != null) {
            String query = "DELETE FROM expenses WHERE id = " + selectedExpense.getId();
            postgres.executaSQL(query);
            loadExpenses();
            AlertMessage.showSuccess("Despesa eliminada correctamente");
        } else {
            AlertMessage.showWarning("Por favor, selecciona una despesa para eliminar");
        }
    }

    @FXML
    private void handleUpdate() {
        Expense selectedExpense = expenseTable.getSelectionModel().getSelectedItem();
        if (selectedExpense != null) {
            try {
                String description = descriptionField.getText();
                double amount = Double.parseDouble(amountField.getText());
                LocalDate date = datePicker.getValue();

                if (date == null) {
                    date = LocalDate.now();
                }

                String query = "UPDATE expenses SET description = '" + description + "', amount = " + amount + 
                             ", date = '" + date + "' WHERE id = " + selectedExpense.getId();
                postgres.executaSQL(query);
                loadExpenses();
                clearFields();
                AlertMessage.showSuccess("Despesa actualizada correctamente");
            } catch (NumberFormatException e) {
                AlertMessage.showError("El importe debe ser un número");
            }
        } else {
            AlertMessage.showWarning("Por favor, selecciona una despesa para actualizar");
        }
    }

    private void clearFields() {
        descriptionField.clear();
        amountField.clear();
        datePicker.setValue(LocalDate.now());
    }
}
