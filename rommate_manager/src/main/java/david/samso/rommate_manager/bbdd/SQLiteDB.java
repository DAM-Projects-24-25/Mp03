package david.samso.rommate_manager.bbdd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class SQLiteDB extends BaseDadesRelacionals {
    protected final String DB_DRIVER = "jdbc:sqlite:";
    public String DB_DIRECTORY;

    public SQLiteDB(String DB_USER, String DB_PORT, String DB_NAME, String DB_HOST, String DB_PASSWORD) {
        super(DB_USER, DB_PORT, DB_NAME, DB_HOST, DB_PASSWORD);
    }

    protected String getStringConnexio() {
        StringBuilder sb = new StringBuilder();
        Objects.requireNonNull(this);
        sb.append("jdbc:sqlite:");
        if (this.DB_DIRECTORY != null && !this.DB_DIRECTORY.isEmpty()) {
            sb.append(this.DB_DIRECTORY).append("/");
        }

        sb.append(this.DB_NAME);
        return sb.toString();
    }

    public Connection getConnexio() throws SQLException {
        return super.getConnection();
    }

    public Connection getConnection(String dbName) throws SQLException {
        String originalDBName = this.DB_NAME;
        this.DB_NAME = dbName;
        Connection conn = this.getConnexio();
        this.DB_NAME = originalDBName;
        return conn;
    }
}
