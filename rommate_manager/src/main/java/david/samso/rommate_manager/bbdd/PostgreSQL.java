package david.samso.rommate_manager.bbdd;

import java.util.Objects;

public class PostgreSQL extends BaseDadesRelacionals {
    protected final String DB_DRIVER = "jdbc:postgresql://";

    public PostgreSQL(String DB_USER, String DB_PORT, String DB_NAME, String DB_HOST, String DB_PASSWORD) {
        super(DB_USER, DB_PORT, DB_NAME, DB_HOST, DB_PASSWORD);
    }

    protected String getStringConnexio() {
        Objects.requireNonNull(this);
        String sb = "jdbc:postgresql://" + this.DB_HOST + ":" + this.DB_PORT + "/" + this.DB_NAME + "?user=" + this.DB_USER + "&password=" + this.DB_PASSWORD;
        return sb;
    }
}
