package david.samso.rommate_manager.bbdd_conect;

import david.samso.rommate_manager.bbdd.PostgreSQL;

public class DataBaseConnection {
    private static final String DEFAULT_PORT = "5432";
    private static final String DB_POSTGRESQL_USER = "alumnedam_58";
    private static final String DB_POSTGRESQL_PASSWORD = "123hola456";
    private static final String DB_POSTGRESQL_HOST = "projectes.ies-eugeni.cat";
    private static final String DB_POSTGRESQL_DB = "alumnedam_58";

    public static PostgreSQL getPostgreSQLConnection() {
        String dbUser = DB_POSTGRESQL_USER;
        String dbPassword = DB_POSTGRESQL_PASSWORD;
        String dbHost = DB_POSTGRESQL_HOST;
        String dbDB = DB_POSTGRESQL_DB;

        if (dbUser == null || dbPassword == null || dbHost == null || dbDB == null) {
            throw new IllegalStateException("Faltan variables de entorno necesarias para la conexión a la base de datos");
        }

        return new PostgreSQL(dbUser, DEFAULT_PORT, dbDB, dbHost, dbPassword);
    }
}
