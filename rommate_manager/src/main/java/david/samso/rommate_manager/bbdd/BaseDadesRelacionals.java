package david.samso.rommate_manager.bbdd;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import javax.swing.JOptionPane;

public abstract class BaseDadesRelacionals {
    protected String DB_USER;
    protected String DB_PORT;
    protected String DB_NAME;
    protected String DB_HOST;
    protected String DB_PASSWORD;

    public BaseDadesRelacionals(String DB_USER, String DB_PORT, String DB_NAME, String DB_HOST, String DB_PASSWORD) {
        this.DB_USER = DB_USER;
        this.DB_PORT = DB_PORT;
        this.DB_NAME = DB_NAME;
        this.DB_HOST = DB_HOST;
        this.DB_PASSWORD = DB_PASSWORD;
    }

    public String getDB_USER() {
        return this.DB_USER;
    }

    public String getDB_PORT() {
        return this.DB_PORT;
    }

    public String getDB_NAME() {
        return this.DB_NAME;
    }

    public String getDB_HOST() {
        return this.DB_HOST;
    }

    public String getDB_PASSWORD() {
        return this.DB_PASSWORD;
    }

    public Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(this.getStringConnexio());
            return connection;
        } catch (SQLException e) {
            System.out.println("NO HI HA CONNEXIÓ A LA BD\n\n");
            JOptionPane.showMessageDialog((Component)null, "NO HI HA CONNEXIÓ A LA BD\nal servidor: " + this.DB_HOST);
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

    protected abstract String getStringConnexio();

    public TableView<ObservableList<String>> obteDadesTableView(String consulta) {
        return null;
    }

    public void executaSQL(String sql) {
        try (
                Connection con = this.getConnection();
                Statement stmt = con.createStatement();
        ) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet executaSQLSelect(String sql) {
        try {
            Connection con = this.getConnection();
            Statement stmt = con.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public TableView obteDadesTableView(ResultSet rs, TableView TVR) throws SQLException {
        for(int i = 0; i < rs.getMetaData().getColumnCount(); ++i) {
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            int finalI = i;
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(((ObservableList)param.getValue()).get(finalI).toString());
                }
            });
        }

        ObservableList data = FXCollections.observableArrayList();

        while(rs.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();

            for(int i = 1; i <= rs.getMetaData().getColumnCount(); ++i) {
                row.add(rs.getString(i));
            }

            System.out.println("Fila [1] afegida " + row);
            data.add(row);
        }

        TVR.setItems(data);
        return TVR;
    }
}