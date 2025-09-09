package david.samso.rommate_manager.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TableView;

public class OracleDB extends BaseDadesRelacionals {
    private static final String DB_PASSWD = "123hola456";
    private final String DB_DRIVER = "oracle.jdbc.OracleDriver";
    private final String DB_DRIVER_TYPE = "thin";

    public OracleDB(String host, String port, String dbName, String user, String passwd) {
        super(user, passwd, host, port, dbName);
    }

    @Override
    public Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:" + DB_DRIVER_TYPE + ":@" + DB_HOST + ":" + DB_PORT + ":" + DB_NAME;
        return DriverManager.getConnection(url, DB_USER, DB_PASSWD);
    }

    @Override
    public String getStringConnexio() {
        return "jdbc:oracle:" + DB_DRIVER_TYPE + ":@" + DB_HOST + ":" + DB_PORT + ":" + DB_NAME;
    }
    
    @Override
    public void executaSQL(String sql) {
        try (Connection conn = getConnection()) {
            conn.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ResultSet executaSQLSelect(String sql) {
        try (Connection conn = getConnection()) {
            return conn.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
