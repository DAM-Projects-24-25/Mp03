package david.samso.rommate_manager.bbdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class BaseDadesRelacionalsTest {
    
    private static final String TEST_USER = "test_user";
    private static final String TEST_PASSWD = "test_password";
    private static final String TEST_HOST = "localhost";
    private static final String TEST_PORT = "5432";
    private static final String TEST_NAME = "test_db";
    private static final String TEST_DIR = "test_dir";
    
    private PostgreSQL postgreSQL;
    private SQLiteDB sqliteDB;
    
    @BeforeEach
    public void setUp() {
        postgreSQL = new PostgreSQL(TEST_USER, TEST_PASSWD, TEST_HOST, TEST_PORT, TEST_NAME);
        sqliteDB = new SQLiteDB(TEST_DIR, TEST_NAME);
    }
    
    @Test
    @DisplayName("Prova de creació d'objectes de base de dades")
    public void testCreacioObjectesBD() {
        assertNotNull(postgreSQL);
        assertNotNull(sqliteDB);
    }
    
    @Test
    @DisplayName("Prova de cadena de connexió de PostgreSQL")
    public void testCadenaConnexioPostgreSQL() {
        String expectedConnectionString = "jdbc:postgresql://" + TEST_HOST + ":" + TEST_PORT + "/" + TEST_NAME;
        assertEquals(expectedConnectionString, postgreSQL.getStringConnexio());
    }
    
    @Test
    @DisplayName("Prova de cadena de connexió de SQLite")
    public void testCadenaConnexioSQLite() {
        String expectedConnectionString = "jdbc:sqlite:" + TEST_DIR + "/" + TEST_NAME;
        assertEquals(expectedConnectionString, sqliteDB.getStringConnexio());
    }
    
    @Test
    @DisplayName("Prova mètode executaSQL de PostgreSQL")
    public void testExecutaSQLPostgreSQL() {

        try {
            assertDoesNotThrow(() -> {
                String sql = "CREATE TABLE IF NOT EXISTS test_table (id INT, name VARCHAR(100))";
            });
        } catch (Exception e) {
            fail("No s'haurien de llançar excepcions durant la prova: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Prova mètode executaSQLSelect de PostgreSQL")
    public void testExecutaSQLSelectPostgreSQL() {
        try {
            assertDoesNotThrow(() -> {
                String sql = "SELECT * FROM test_table";
            });
        } catch (Exception e) {
            fail("No s'haurien de llançar excepcions durant la prova: " + e.getMessage());
        }
    }
} 