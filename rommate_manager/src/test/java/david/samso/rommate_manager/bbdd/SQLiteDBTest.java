package david.samso.rommate_manager.bbdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Disabled;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class SQLiteDBTest {
    
    private static final String TEST_DIR = "test_dir";
    private static final String TEST_NAME = "test_db.db";
    
    private SQLiteDB sqliteDB;
    
    @BeforeEach
    public void setUp() {
        sqliteDB = new SQLiteDB(TEST_DIR, TEST_NAME);
    }
    
    @Test
    @DisplayName("Prova de creació de l'objecte SQLiteDB")
    public void testCreacioObjecteSQLiteDB() {
        assertNotNull(sqliteDB);
        assertEquals("", sqliteDB.DB_USER);
        assertEquals("", sqliteDB.DB_PASSWD);
        assertEquals("", sqliteDB.DB_HOST);
        assertEquals("", sqliteDB.DB_PORT);
        assertEquals(TEST_NAME, sqliteDB.DB_NAME);
    }
    
    @Test
    @DisplayName("Prova de la cadena de connexió")
    public void testCadenaConnexio() {
        String expectedConnectionString = "jdbc:sqlite:" + TEST_DIR + "/" + TEST_NAME;
        assertEquals(expectedConnectionString, sqliteDB.getStringConnexio());
    }
    
    @Test
    @Disabled("Aquesta prova requereix accés a un sistema de fitxers")
    @DisplayName("Prova de connexió a SQLite")
    public void testConnexio() {
        try {
            Connection conn = sqliteDB.getConnection();
            assertNotNull(conn);
            assertFalse(conn.isClosed());
            
            conn.close();
            assertTrue(conn.isClosed());
        } catch (SQLException e) {
            fail("No s'hauria de llançar cap excepció: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Prova del mètode executaSQL")
    public void testExecutaSQL() {
        assertDoesNotThrow(() -> {
            sqliteDB.executaSQL("CREATE TABLE IF NOT EXISTS test_table (id INT, name VARCHAR(100))");
        });
    }
    
    @Test
    @DisplayName("Prova del mètode executaSQLSelect")
    public void testExecutaSQLSelect() {
        assertDoesNotThrow(() -> {
            sqliteDB.executaSQLSelect("SELECT 1");
        });
    }
} 